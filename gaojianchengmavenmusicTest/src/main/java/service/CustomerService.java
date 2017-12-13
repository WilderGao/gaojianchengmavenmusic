package service;

import model.Feedback;
import model.ReceiveTo;
import model.User;
import org.apache.ibatis.annotations.SelectKey;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public interface CustomerService {

    Feedback loginCustomer(User user);

    /**
     * 得到验证码
     * @param authCode
     * @return
     */
    int GetAccount(String authCode);

    /**
     * 判断注册信息的service
     * @param receiveTo
     * @param accountCheck
     * @param map
     * @return
     */
    Feedback registerCustomer(ReceiveTo<User> receiveTo, String accountCheck , Map<String , String> map);
}
