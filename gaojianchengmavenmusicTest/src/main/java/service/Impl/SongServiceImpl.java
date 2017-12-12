package service.Impl;

import com.github.pagehelper.PageHelper;
import dao.SongDao;
import enums.StatusEnum;
import model.DownloadModel;
import model.Feedback;
import model.WishModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.SongService;

import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Service
//设置事务
@Transactional(rollbackFor = Exception.class)
public class SongServiceImpl implements SongService {
    @Autowired
    private SongDao songDao;

    @Override
    public Feedback<List<WishModel>> handleWish(int pageNum, int pageSize) {
        Feedback<List<WishModel>> feedback = new Feedback<>();
        //判断查询的页码错误与否
        if (pageNum <= 0 || pageSize <= 0){
            feedback.setStatus(StatusEnum.PAGE_NUM_ERROR.getState());
            return feedback;
        }else {
            PageHelper.startPage(pageNum,pageSize);
            List<WishModel> wishModelList = songDao.getWishList();
            feedback.setStatus(StatusEnum.OK.getState());
            feedback.setData(wishModelList);
            return feedback;
        }
    }

    @Override
    public Feedback<Integer> uploadSongService(DownloadModel downloadModel) {
        Feedback<Integer> feedback = new Feedback<>();
        if (null == downloadModel || downloadModel.getCustomerId() == 0 || null == downloadModel.getPlayUrl()){
            //获得内容为空，没有对应用户的Id，歌曲对应的url为空的时候都判断为错误
            feedback.setStatus(StatusEnum.METHOD_ERROR.getState());
            return feedback;
        }else {
            songDao.uploadSong(downloadModel);
            feedback.setStatus(StatusEnum.OK.getState());
            return feedback;
        }
    }


}
