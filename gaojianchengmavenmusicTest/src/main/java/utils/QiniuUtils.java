package utils;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
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
     * 数据流将文件上传到七牛云上，并返回对应的URL存到数据库进行处理
     * @param fileName 文件名
     * @return  存放在七牛云的URL
     */
    public static final String uploadFileToQiniu(String fileName , String songUrl) throws IOException {
        //声明接上七牛服务器的哪一个分区，zone2代表华南分区
        Configuration configuration = new Configuration(Zone.zone2());
        //上传管理器
        UploadManager uploadManager = new UploadManager(configuration);
        Auth auth = Auth.create(ACCESS_KEY , SECRET_KEY);
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
        putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        putPolicy.put("callbackBodyType", "application/json");
        long expireSeconds = 3600*24*365*10;

        BufferedInputStream songUrlInputStream = new BufferedInputStream(new URL(songUrl).openStream());

        //文件名称
        String key = fileName+".mp3";
        String upToken = auth.uploadToken(BUCKET);
        try {
            Response response = uploadManager.put(songUrlInputStream , key ,upToken ,null , null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
            return "http://"+getRequestToQiniuUrl(key);

        } catch (QiniuException e) {
            Response response = e.response;
            System.out.println(response.getInfo());
            e.printStackTrace();
            try {
                System.out.println(response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    private static String getRequestToQiniuUrl(String fileName){
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
