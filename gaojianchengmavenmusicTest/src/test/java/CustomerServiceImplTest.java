import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.InsertSongDao;
import dao.LoginDao;
import model.DownloadModel;
import model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.Impl.CustomerServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class CustomerServiceImplTest extends AbstractJUnit4SpringContextTests{
//
//    @Autowired
//    private CustomerServiceImpl customerService;
    @Autowired
    private InsertSongDao insertSongDao;

//
//    @Repeat(2)
//    @Test(timeout = 2000)
//    @DirtiesContext()
//    public void customerServiceTest(){
//        User user = new User();
//        user.setUserEmail("845956752@qq.com");
//        user.setPassword("jiancheng");
//        customerService.loginCustomer(user);
//    }

    @Test
    public void testPageHelper(){
        //执行查询并分页
        PageHelper.startPage(1,4);
        List<DownloadModel> models = insertSongDao.getSongs(1);
        PageInfo<DownloadModel> modelPageInfo = new PageInfo<>(models);
        for (DownloadModel model : models) {
            System.out.println(model);
        }
        System.out.println("长度为"+models.size());
    }
}
