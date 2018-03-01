package dao;

import model.DownloadModel;
import model.WishModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @Author:高键城
 * @time：
 * @Discription：
 */
public interface SongDao {
    /**
     * 获得愿望列表，当userId为-1是获取所有列表，有特定参数时获取单个用户的愿望
     * @param userId
     * @return
     */
    List<WishModel> getWishList(@Param("userId") int userId);

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
     * 获得某条特定的愿望，通过Id来获得
     * @param wishId
     * @param songName
     * @param singerName
     * @return
     */
    DownloadModel selectWish(@Param("wishId") int wishId , @Param("songName")String songName,@Param("singerName") String singerName);

    /**
     * 获得某个人的愿望清单
     * @param userId 用户Id
     * @return
     */
    List<WishModel> selectWishAboutUser(@Param("userId") int userId);

    /**
     * 插入愿望
     * @param wishModel 愿望模型
     * @return
     */
    int insertWish(WishModel wishModel);

    /**
     * 获取服务器歌曲信息
     * @param information
     * @return
     */
    List<DownloadModel> selectServerSong(@Param("information") String information);



}
