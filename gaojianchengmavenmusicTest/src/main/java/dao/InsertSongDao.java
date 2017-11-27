package dao;

import model.DownloadModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public interface InsertSongDao {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void InsertSongCloud(@Param("customer_id")long customerId , @Param("img_url")String imgURL ,
                         @Param("song_name") String songName , @Param("singer_name")String singerName,
                         @Param("play_url")String playURL);
    List<DownloadModel> GetSongs(@Param("customer_id")long customerId);
}
