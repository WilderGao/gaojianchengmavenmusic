package utils;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public final class QiniuUtils {
    private static final String ACCESS_KEY = "JTzzyirY3g84GgVl-LsFePbusNOx1xWjWp-oLEMl";
    private static final String SECRET_KEY = "Rw1vCPSY5gfn-ia__vhSt9GwofLu501V_Ecr6wzl";
    private static final String BUCKET = "wilderg";
    private static final String DOMAIN_OF_BUCKET = "ozwr11exu.bkt.clouddn.com";
    /**
     * 将对应的本地资源上传到七牛云上，并返回对应的URL存到数据库进行处理
     * @param localFilePath 本地资源的访问路径
     * @return  存放在七牛云的URL
     */
    public static final String uploadFileToQiniu(String localFilePath){
        //声明接上七牛服务器的哪一个分区，zone2代表华南分区
        Configuration configuration = new Configuration(Zone.zone2());
        //上传管理器
        UploadManager uploadManager = new UploadManager(configuration);
        Auth auth = Auth.create(ACCESS_KEY , SECRET_KEY);
        //文件名称
        String key = localFilePath.substring(localFilePath.lastIndexOf("\\")+1,localFilePath.length());
        String upToken = auth.uploadToken(BUCKET);

        try {
            Response response = uploadManager.put(localFilePath , key ,upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
            System.out.println("得到的key为: "+putRet.key);
            System.out.println("得到的hash为: "+putRet.hash);
            return getRequestToQiniuUrl(key);

        } catch (QiniuException e) {
            Response response = e.response;
            System.out.println(response.toString());
            try {
                System.out.println(response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public static String getRequestToQiniuUrl(String fileName){
        String encodingFileName = null;
        try {
            encodingFileName = URLEncoder.encode(fileName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String finalUrl = String.format("%s/%s",DOMAIN_OF_BUCKET,encodingFileName);
        return finalUrl;
    }
}
