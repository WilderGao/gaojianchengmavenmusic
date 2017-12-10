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
     * @return
     */
    Feedback<List<WishModel>> handleWish(int pageNum , int pageSize);

    /**
     * 上传歌曲后将信息记录在数据库中
     * @param downloadModel 上传歌曲的实体类
     * @return
     */
    Feedback<Integer> uploadSongService(DownloadModel downloadModel);
}
