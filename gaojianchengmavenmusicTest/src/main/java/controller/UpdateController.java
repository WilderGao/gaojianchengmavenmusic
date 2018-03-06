package controller;

import model.Feedback;
import model.Notice;
import model.VersionUpdate;
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

    /**
     * 通知接口暂时关闭
     */
    @GetMapping(value = "/version")
    @ResponseBody
    public Feedback<VersionUpdate> noticeInform(@Param("versionCode")int versionCode){
        return updateService.checkVersion(versionCode);
    }


    /**
     * 返回一个小小的公告给APP显示
     * @return
     */
    @GetMapping(value = "/notice")
    @ResponseBody
    public Feedback<Notice> notice(){
        return updateService.simpleNotice();
    }

}
