package utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * @time：
 * @Discription：生成随机验证码
 */
public final class CountUtils {
    private static final String pattern = "[\\w]+@[0-9a-z]+(\\.+[a-z]{2,4}){1,3}";
    private static int OK = 1 ;
    private static  int EMAIL_ERROR = -1;

    /**
     * 生成随机的验证码
     * @return 验证码字符串
     */
    public static String getRandomString(){
        int LENGTH = 4 ;
        String base = "0123456789";
        Random random = new Random();
        int stringLength  = base.length();
        int number ;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < LENGTH ;i++){
             number = random.nextInt(stringLength);
             stringBuilder.append(base.charAt(number));
        }
        return stringBuilder.toString();
    }

    /**
     * 邮箱验证
     * @param email 邮箱
     * @return
     */
    public static int CompileEmail(String email){
        Pattern checkMail=Pattern.compile(pattern);
        Matcher matchMail=checkMail.matcher(email);
        if (matchMail.matches()){
            return OK;
        }else{
            return EMAIL_ERROR;
        }
    }

}
