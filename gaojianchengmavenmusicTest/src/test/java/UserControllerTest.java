import controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(locations = "classpath:spring.xml"),
        @ContextConfiguration(locations = "classpath:springmvc.xml")
})

@TransactionConfiguration(transactionManager = "transactionManager" ,defaultRollback = true)
@Transactional

@WebAppConfiguration
public class UserControllerTest {
    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp(){
        //初始化MockMvc对象
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

@Test
    public void TestMethodLogin() throws Exception {
//        User user = new User();
//        user.setUserEmail("845956752@qq.com");
//        user.setPassword("123456");
        String responseString = mockMvc.perform(
                get("/download/detail")
        .contentType(MediaType.APPLICATION_JSON).param("userId", String.valueOf(9))).andExpect(status().isOk()).andDo(
                print()).andReturn().getResponse().getContentAsString();
    System.out.println("---------返回的json"+responseString);
}

}
