package dao;

import model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public interface LoginDao {
    public User SelectUser(@Param("customer_email") String customerEmail);
    public void InsertUser(@Param("email")String email , @Param("password")String password);
}
