package utils;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 与查询分页有关的工具类
 */
public final class PageHelperUtils {
//    private static String imgPath = "saved/saveImg";
//    private static String playPath = "saved/savePlay";
//    public static String getImgPath(String rootPath){
//        String ImgPath = rootPath+imgPath;
//        File ImgFile = new File(ImgPath);
//        if (!ImgFile.exists()) {
//            //创建路径
//            ImgFile.mkdirs();
//        }
//        return ImgPath;
//    }
//
//    public static String getPlayPath(String rootPath){
//        String PlayPath = rootPath+playPath;
//        File PlayFile = new File(PlayPath);
//        if (!PlayFile.exists()) {
//            //创建路径
//            PlayFile.mkdirs();
//        }
//        return PlayPath;
//    }

    /**
     * 实现分页
     * @param pageNum   第几页
     * @param pageSize  一页几个
     * @param list      需要分页的集合
     * @return
     */
    public static List pageToSelect(int pageNum , int pageSize , List list){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        return list;
    }


}
