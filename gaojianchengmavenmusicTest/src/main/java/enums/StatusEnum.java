package enums;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public enum StatusEnum {
    OK(1),         //返回正常
    USER_ERROR(110),    //传来的User为空
    ACCOUNT_NOT_EXIST(250),     //账户不存在
    PASSWORD_ERROR(300),        //密码错误
    ACCOUNT_ALREADY_EXIST(400),    //账户已存在
    EMAIL_COUNT_ERROR(450),      // 邮箱格式错误
    EMAIL_ERROR(600),           //以为我们傻逼？
    AUTH_NULL(650),             //验证码生成有误
    URL_NULL(700),              //URL为空
    INSERT_SQL_ERROR(750),      //插入数据库错误
    ALREADY_LASTEST(780),          //已经是最新版本
    VERSION_ERROR(790),             //版本号异常
    UPLOAD_CLOUD_ERROR(800);        //上传云服务器有误

    private int state;

    StatusEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
