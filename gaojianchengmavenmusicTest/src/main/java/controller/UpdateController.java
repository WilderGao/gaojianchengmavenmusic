package controller;

import model.Feedback;
import model.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.Impl.UpdateServiceImpl;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Controller
@RequestMapping("/update")
public class UpdateController {
    @Autowired
    private UpdateServiceImpl updateService;

    @GetMapping(value = "/notice")
    @ResponseBody
    public Feedback<Notice> noticeInform(@Param("userId") int userId , @Param("versionCode")int versionCode){
        return updateService.checkVersion(userId , versionCode);
    }

}
