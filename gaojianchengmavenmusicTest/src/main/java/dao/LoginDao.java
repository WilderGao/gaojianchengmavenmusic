package dao;

import model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public interface LoginDao {
    User SelectUser(@Param("customer_email") String customerEmail);
    void InsertUser(User user);

    /**
     * 通过faceId查找客户的信息
     * @param faceId
     * @return
     */
    User selectUserByFaceId(@Param("face_id") String faceId);

}
