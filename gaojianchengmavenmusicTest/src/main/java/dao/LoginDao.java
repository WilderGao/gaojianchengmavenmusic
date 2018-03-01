package dao;

import model.DownloadModel;
import model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public interface LoginDao {

    /**
     * 查找用户是否存在
     * @param customerEmail
     * @return
     */
    User selectUser(@Param("userEmail") String customerEmail);

    /**
     * 插入新用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 通过faceId查找客户的信息
     * @param faceId
     * @return
     */
    User selectUserByFaceId(@Param("face_id") String faceId);


    List<DownloadModel> asdff();

    void insertList(DownloadModel strings);

}
