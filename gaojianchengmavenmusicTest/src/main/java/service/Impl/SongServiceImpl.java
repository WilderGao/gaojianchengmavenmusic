package service.Impl;

import com.github.pagehelper.PageHelper;
import dao.SongDao;
import enums.StatusEnum;
import model.Feedback;
import model.WishModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SongService;

import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Service
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
}
