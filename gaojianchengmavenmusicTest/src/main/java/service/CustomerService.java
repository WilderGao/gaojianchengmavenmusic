package service;

import model.Feedback;
import model.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public interface CustomerService {
    public Feedback loginCustomer(User user);
    public int GetAccount(String authCode);
    //对注册的账户进行判断
    public Feedback registerCustomer(User user , String accountCheck , Map<String , String> map);
}
