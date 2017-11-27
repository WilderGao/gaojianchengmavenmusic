package utils;

import java.io.File;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public final class FileUtils {
    private static String imgPath = "saved/saveImg";
    private static String playPath = "saved/savePlay";
    public static String getImgPath(String rootPath){
        String ImgPath = rootPath+imgPath;
        File ImgFile = new File(ImgPath);
        if (!ImgFile.exists()) {
            //创建路径
            ImgFile.mkdirs();
        }
        return ImgPath;
    }

    public static String getPlayPath(String rootPath){
        String PlayPath = rootPath+playPath;
        File PlayFile = new File(PlayPath);
        if (!PlayFile.exists()) {
            //创建路径
            PlayFile.mkdirs();
        }
        return PlayPath;
    }


}
