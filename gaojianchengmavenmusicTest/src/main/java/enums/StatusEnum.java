package enums;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public enum StatusEnum {
    OK(1),         //返回正常
    REGISTER_METHOD_ERROR(50),     //参数有误
    USER_ERROR(110),    //传来的User为空
    ACCOUNT_NOT_EXIST(250),     //账户不存在
    PASSWORD_ERROR(300),        //密码错误
    NAME_NULL_ERROR(350),       //用户名为空
    ACCOUNT_ALREADY_EXIST(400),    //账户已存在
    EMAIL_COUNT_ERROR(450),      // 邮箱格式错误
    EMAIL_ERROR(600),           //以为我们傻逼？
    AUTH_NULL(650),             //验证码生成有误
    URL_NULL(700),              //URL为空
    INSERT_SQL_ERROR(750),      //插入数据库错误
    ALREADY_LASTEST(780),          //已经是最新版本
    VERSION_ERROR(790),             //版本号异常
    UPLOAD_CLOUD_ERROR(800),        //上传云服务器有误
    PAGE_NUM_ERROR(810),            //页码数有误
    METHOD_ERROR(444),              //传入参数有误
    DESIRE_EXIST(1123);             //已经存在相同的愿望
    private int state;

    StatusEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
