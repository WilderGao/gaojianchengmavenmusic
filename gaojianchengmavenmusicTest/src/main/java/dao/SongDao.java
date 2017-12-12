package dao;

import model.DownloadModel;
import model.WishModel;

import java.util.List;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public interface SongDao {
    /**
     * 获得愿望列表
     * @return
     */
    List<WishModel> getWishList();

    /**
     * 上传歌曲
     * @param model 歌曲模型
     * @return
     */
    int uploadSong(DownloadModel model);
}
