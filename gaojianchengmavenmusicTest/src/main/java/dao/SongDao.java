package dao;

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
}
