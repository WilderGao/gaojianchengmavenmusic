import dao.LoginDao;
import model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.Impl.CustomerServiceImpl;

import javax.annotation.Resource;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class CustomerServiceImplTest extends AbstractJUnit4SpringContextTests{

    @Autowired
    private CustomerServiceImpl customerService;


    @Repeat(2)
    @Test(timeout = 2000)
    @DirtiesContext()
    public void customerServiceTest(){
        User user = new User();
        user.setUserEmail("845956752@qq.com");
        user.setPassword("jiancheng");
        customerService.loginCustomer(user);
    }
}
