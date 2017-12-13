package dao;

import model.DownloadModel;
import model.WishModel;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 实现愿望
     * @param wishModel 愿望模型
     * @return 整个愿望具体内容
     */
    int updateWishState(WishModel wishModel);

    /**
     * 获取某一个愿望
     * @param wishId 愿望Id
     * @return 下载类
     */
    DownloadModel selectWishById(@Param("wishId") int wishId);

    /**
     * 获得某个人的愿望清单
     * @param userId 用户Id
     * @return
     */
    List<WishModel> selectWishAboutUser(@Param("userId") int userId);
}
