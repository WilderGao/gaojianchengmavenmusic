package service;

import model.Feedback;
import model.WishModel;

import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public interface SongService {
    Feedback<List<WishModel>> handleWish(int pageNum , int pageSize);
}
