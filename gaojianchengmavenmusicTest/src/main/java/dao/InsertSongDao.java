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
     * 将上传的数据内容增加到服务器
     * @param customerId
     * @param imgURL
     * @param songName
     * @param singerName
     * @param playURL
     * @param albumName
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackFor = Throwable.class)
    void insertSongCloud(@Param("customer_id")long customerId , @Param("img_url")String imgURL ,
                         @Param("song_name") String songName , @Param("singer_name")String singerName,
                         @Param("play_url")String playURL,@Param("album_name")String albumName);

    /**
     * 通过id获取下载的歌曲列表
     * @param customerId id
     * @return
     */
    List<DownloadModel> getSongs(@Param("customer_id")long customerId);
}
