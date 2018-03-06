package controller;

import dao.LoginDao;
import enums.RequestLimit;
import enums.StatusEnum;
import model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import service.Impl.CustomerServiceImpl;
import service.Impl.SongServiceImpl;
import utils.AccountTask;
import utils.CountUtils;
import utils.MailUtils;
import utils.PatternUtils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final CustomerServiceImpl customerService;
    private final SongServiceImpl songService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Autowired
    private LoginDao loginDao;

    @Autowired(required = false)
    public UserController(CustomerServiceImpl customerService,SongServiceImpl songService) {
        this.customerService = customerService;
        this.songService = songService;
    }



    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    @ResponseBody
    public Feedback<User> Login(@RequestBody @Valid User user , BindingResult result){
        Feedback<User> feedback = new Feedback<>();
        System.out.println(user);
        if (result.hasErrors()){
          feedback.setStatus(StatusEnum.METHOD_ERROR.getState());
          return feedback;
        }
        feedback = customerService.loginCustomer(user);
        return feedback;
    }

    /**
     * 获取验证码的请求，一分钟之内最多请求两次
     * @param userEmail
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    @RequestMapping(value = "/getcount")
    @ResponseBody
    @RequestLimit(time = 6000 , count = 2)
    public Feedback<User> GetCount(@Param("userEmail") String userEmail , HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        Feedback<User> feedback = new Feedback<>();
        final User user = new User();
        user.setUserEmail(userEmail);
        //获得验证码
        String authCode = CountUtils.getRandomString();
        SessionMap.emailMap.put(user.getUserEmail(),authCode);
//        //判断让session移除某个键值对
        executorService.submit(new AccountTask(userEmail));

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
    @RequestLimit(time = 6000 , count = 2)
    public Feedback<Integer> Resign(@RequestBody ReceiveTo<User> receiveTo , HttpServletRequest request){
            User user = receiveTo.getData();
            String accountCheck = SessionMap.emailMap.get(user.getUserEmail());
            System.out.println("Map中的验证码为"+accountCheck);
            System.out.println("user中的验证码"+user.getRegisterCount());
            Feedback<Integer> feedback = customerService.registerCustomer(receiveTo, accountCheck, SessionMap.emailMap);
            return feedback;
        }


    @RequestMapping("/insertsingerpic")
    @ResponseBody
    public void insert() throws IOException {
        List<DownloadModel> strings = loginDao.asdff();
        System.out.println(strings);
        for (DownloadModel downloadModel : strings) {
            downloadModel.setSingerUrl(PatternUtils.getSingerPicUrl(downloadModel.getSingerName()));
            loginDao.insertList(downloadModel);
        }
    }

}
