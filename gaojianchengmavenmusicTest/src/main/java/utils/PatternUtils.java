package utils;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

/**
 * @author:Wilder Gao
 * @time:2017/12/15
 * @Discription：判断正则表达式的工具类
 */
public final class PatternUtils {
    public static final String NULL_PATTERN = "not found";
    /**
     * 获得访问歌手图片的url
     * @param singerName 歌手名称
     * @return
     * @throws IOException
     */
    public static String getSingerPicUrl(String singerName) throws IOException {
        singerName = singerName.replaceAll(" ","%20");
        String urlStringToGetMID = "https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.song&searchid=55507813784720573&t=0&aggr=1&cr=1&catZhida=1&lossless=0&flag_qc=0&p=1&n=20&w="+singerName+"&g_tk=5381&jsonpCallback=searchCallbacksong912&loginUin=0&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0";
        URL url = new URL(urlStringToGetMID);
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = streamReader.readLine();
        line = line.substring(line.indexOf("(")+1,line.lastIndexOf(")"));
        Gson gson = new Gson();
        Map map = gson.fromJson(line, Map.class);
        LinkedTreeMap map1 = (LinkedTreeMap) map.get("data");
        if (map1 == null){
            return NULL_PATTERN;
        }
        LinkedTreeMap map2 = (LinkedTreeMap) ((LinkedTreeMap) map1.get("zhida")).get("zhida_singer");
        if (map2 == null){
            return NULL_PATTERN;
        }
        String singerMID = (String) map2.get("singerMID");
        String urlToGetSingerPic = "https://y.gtimg.cn/music/photo_new/T001R300x300M000"+singerMID+".jpg?max_age=2592000";
        return urlToGetSingerPic;
    }


}
