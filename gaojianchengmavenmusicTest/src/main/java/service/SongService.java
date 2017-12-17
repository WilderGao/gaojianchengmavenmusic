package service;

import model.DownloadModel;
import model.Feedback;
import model.WishModel;

import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public interface SongService {

    /**
     * 获取愿望列表，分页查询
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    Feedback<List<WishModel>> handleWish(int pageNum , int pageSize , int userId);

    /**
     * 上传歌曲后将信息记录在数据库中
     * @param downloadModel 上传歌曲的实体类
     * @return
     */
    Feedback<Integer> uploadSongService(DownloadModel downloadModel);

    /**
     * 实现愿望
     * @param wishModel 愿望类
     * @return
     */
    Feedback<String> achieveSongService(WishModel wishModel);

    /**
     * 获得歌手或者歌手的歌曲
     * @param pageNum
     * @param pageSize
     * @param singerName
     * @return
     */
    Feedback<List<DownloadModel>> getMavenMusic(int pageNum , int pageSize , String singerName);

    /**
     * 获得某个用户下载的歌曲
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Feedback<List<WishModel>> selectUserSongService(int userId , int pageNum , int pageSize);

    /**
     * 用户愿望请求
     * @param wishModel 匹配歌手名或者歌曲名
     * @return
     */
    Feedback<String> desireMusicService(WishModel wishModel);

    /**
     * 搜索歌曲
     * @param information
     * @return
     */
    Feedback<List<DownloadModel>> searchSongService(String information);
}
