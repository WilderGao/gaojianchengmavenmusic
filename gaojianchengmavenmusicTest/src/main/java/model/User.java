package model;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public class User implements Serializable{
    private int userId;
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "{user.userEmail.illegal}")
    private String userEmail ;

    @Length(min = 6 , max = 20 , message = "{user.password.length.illegal}")
    private String password ;

    private String customerName;
    private String registerCount ;
    private String faceId;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(String registerCount) {
        this.registerCount = registerCount;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", customerName='" + customerName + '\'' +
                ", registerCount='" + registerCount + '\'' +
                ", faceId='" + faceId + '\'' +
                '}';
    }
}
