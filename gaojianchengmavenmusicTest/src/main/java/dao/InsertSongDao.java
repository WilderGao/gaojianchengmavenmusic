package dao;

import model.DownloadModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public interface InsertSongDao {

    /**
     * 将内容插入到服务器
     * @param model
     */
    void insertSongCloud(DownloadModel model);

    /**
     * 通过id获取下载的歌曲列表
     * @param customerId id
     * @return 歌曲列表
     */
    List<DownloadModel> getSongs(@Param("customer_id")long customerId);

    /**
     * 获得服务器音乐区的歌曲信息
     * @return
     */
    List<DownloadModel> getMavenMusicSongs();
}
