package controller;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import enums.StatusEnum;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.Impl.CustomerServiceImpl;
import utils.CountUtils;
import utils.MailUtils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final CustomerServiceImpl customerService;

    @Autowired(required = false)
    public UserController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    @ResponseBody
    public Feedback<Integer> Login(@RequestBody @RequestParam("user") User user){
        Feedback<Integer> feedback = customerService.loginCustomer(user);
        return feedback;
    }

    @RequestMapping(value = "/getcount")
    @ResponseBody
    public Feedback<User> GetCount(HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        Feedback<User> feedback = new Feedback<>();
        final User user = new User();
        user.setUserEmail(request.getParameter("userEmail"));
        //获得验证码
        String authCode = CountUtils.getRandomString();
        SessionMap.emailMap.put(user.getUserEmail(),authCode);
        System.out.println("authCode：   "+user.getUserEmail());
//        //判断让session移除某个键值对
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                long end ;
                while(true){
                    end = System.currentTimeMillis();
                    if ((end - start)>1000*60*2) {
                        SessionMap.emailMap.remove(user.getUserEmail());
                        break;
                    }
                    try {
                        Thread.sleep(5000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        int result = customerService.GetAccount(authCode);
        if (result == StatusEnum.OK.getState()) {
            user.setRegisterCount(authCode);
            feedback.setStatus(StatusEnum.OK.getState());
            MailUtils.sent(user.getUserEmail() , authCode);
            return feedback;
        }else {
            feedback.setStatus(StatusEnum.AUTH_NULL.getState());
            return feedback;
        }
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    @ResponseBody
    public Feedback<Integer> Resign(@RequestBody ReceiveTo<User> receiveTo, HttpSession session){
            User user = receiveTo.getData();
            String accountCheck = SessionMap.emailMap.get(user.getUserEmail());
            System.out.println(accountCheck + "  session中的验证码\n" + user.getRegisterCount() + "   传过来的验证码");
            Feedback<Integer> feedback = customerService.registerCustomer(receiveTo, accountCheck, SessionMap.emailMap);
            return feedback;
        }

}
