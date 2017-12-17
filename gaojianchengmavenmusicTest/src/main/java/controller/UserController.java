package controller;

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
import utils.CountUtils;
import utils.MailUtils;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

    @Autowired(required = false)
    public UserController(CustomerServiceImpl customerService,SongServiceImpl songService) {
        this.customerService = customerService;
        this.songService = songService;
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    @ResponseBody
    public Feedback<Integer> Login(@RequestBody @Valid User user , BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            System.out.println("报错啦草拟吗"+errors.size());
            for (ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
        }
        Feedback<Integer> feedback = customerService.loginCustomer(user);
        return feedback;
    }

    @RequestMapping(value = "/getcount")
    @ResponseBody
    public Feedback<User> GetCount(@Param("userEmail") String userEmail) throws UnsupportedEncodingException, MessagingException {
        Feedback<User> feedback = new Feedback<>();
        final User user = new User();
        user.setUserEmail(userEmail);
        //获得验证码
        String authCode = CountUtils.getRandomString();
        SessionMap.emailMap.put(user.getUserEmail(),authCode);
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
    public Feedback<Integer> Resign(@RequestBody ReceiveTo<User> receiveTo ){
            User user = receiveTo.getData();
            String accountCheck = SessionMap.emailMap.get(user.getUserEmail());
            Feedback<Integer> feedback = customerService.registerCustomer(receiveTo, accountCheck, SessionMap.emailMap);
            return feedback;
        }


//    public Feedback<List<WishModel>> wantList(@Param("userId")int userId,
//                                              @Param("pageNum")int pageNum,
//                                              @Param("pageSize")int pageSize){
//        return songService.selectUserSongService(userId,pageNum,pageSize);
//    }

}
