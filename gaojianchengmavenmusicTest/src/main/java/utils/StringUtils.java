package utils;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public final class StringUtils {
    private static String URL = "http://120.77.38.183:8080/gaojiancheng.mavenmusic/";
    private static String IMG_URL = "saved/saveImg";
    private static String PLAY_URL = "saved/savePlay";

    /**
     * 返回去的图片下载连接
     * @param imgOldString
     * @return
     */
    public static String ImgString(String imgOldString){
        imgOldString = imgOldString.substring(imgOldString.lastIndexOf("/"));
        return URL+IMG_URL+imgOldString;
    }

    /**
     * 返回去的音乐下载连接
     * @param playOldString
     * @return
     */
    public static String PlayString(String playOldString){
        playOldString = playOldString.substring(playOldString.lastIndexOf("/"));
        return URL+PLAY_URL+playOldString;
    }

}
