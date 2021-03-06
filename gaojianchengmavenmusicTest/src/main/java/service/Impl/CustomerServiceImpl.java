package service.Impl;

import dao.LoginDao;
import enums.StatusEnum;
import model.Feedback;
import model.ReceiveTo;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CustomerService;
import utils.CountUtils;

import java.util.Map;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {
    private final int FACE_REGISTER = 1;
    private final int NORMAL_REGISTER = 2;
    private LoginDao loginDao;
    private Feedback<User> feedback;

    @Autowired(required = false)
    public CustomerServiceImpl(LoginDao loginDao){
        this.loginDao = loginDao;
    }

    @Override
    public Feedback loginCustomer(User user) {
      if (user == null){
          feedback = new Feedback<>();
          feedback.setStatus(StatusEnum.USER_ERROR.getState());
          return  feedback;
      }if (-1 == CountUtils.CompileEmail(user.getUserEmail())){
          feedback = new Feedback<>();
          feedback.setStatus(StatusEnum.EMAIL_COUNT_ERROR.getState());
          return feedback;
        }

        User userCheck = loginDao.selectUser(user.getUserEmail());

      if (userCheck == null){
          feedback = new Feedback<>();
          feedback.setStatus(StatusEnum.ACCOUNT_NOT_EXIST.getState());
          return feedback;
      }else if (!user.getPassword().equals(userCheck.getPassword())){
          feedback = new Feedback<>();
          feedback.setStatus(StatusEnum.PASSWORD_ERROR.getState());
          return feedback;
      }

          feedback = new Feedback<>();
          feedback.setStatus(StatusEnum.OK.getState());
          feedback.setData(userCheck);
          return feedback;
    }

    @Override
    public int GetAccount(String authCode) {
        Feedback<String> feedback = new Feedback<>();
        if (authCode == null){
            return StatusEnum.AUTH_NULL.getState();
        }else {
            return StatusEnum.OK.getState();
        }
    }

    @Override
    public Feedback registerCustomer(ReceiveTo<User> receiveTo, String accountCheck , Map<String , String> map) {
        Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
        Feedback<String> feedback = new Feedback<>();
        //首先要判断是刷脸注册还是普通注册
        int method = receiveTo.getMethod();
        User user = receiveTo.getData();
        if (method == NORMAL_REGISTER ) {
            //判断是普通注册
            if (!user.getRegisterCount().equals(accountCheck)) {
                LOGGER.error("====验证码错误====");
                feedback.setStatus(StatusEnum.AUTH_NULL.getState());
                return feedback;
            }else if (user.getCustomerName() == null){
                LOGGER.error("====没有填写用户名====");
                feedback.setStatus(StatusEnum.NAME_NULL_ERROR.getState());
                return feedback;
            }
            User userCheck = loginDao.selectUser(user.getUserEmail());
            if (userCheck != null) {
                feedback.setStatus(StatusEnum.ACCOUNT_ALREADY_EXIST.getState());
                return feedback;
            } else {
                try {
                    loginDao.insertUser(user);
                    map.remove(user.getUserEmail());
                    feedback.setStatus(StatusEnum.OK.getState());
                    return feedback;
                } catch (Exception e) {
                    throw new RuntimeException("insert Fail " + e);
                }
            }
        }else if (method == FACE_REGISTER){
            //判断是刷脸注册
            User checkUserByFaceId = loginDao.selectUserByFaceId(user.getFaceId());
            if (checkUserByFaceId != null){
                feedback.setStatus(StatusEnum.ACCOUNT_ALREADY_EXIST.getState());
                return feedback;
            }else {
                loginDao.insertUser(user);
                //刷脸注册不需要验证码
                feedback.setStatus(StatusEnum.OK.getState());
                return feedback;
            }
        }else {
            //都不符合表明注册参数有误
            feedback.setStatus(StatusEnum.REGISTER_METHOD_ERROR.getState());
            return feedback;
        }
    }
}
