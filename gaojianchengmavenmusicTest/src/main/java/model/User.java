package model;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public class User {
    private int userId;
    private String userEmail ;
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
