package controller;

import model.Feedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.Impl.UpdateServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Controller
@RequestMapping("/update")
public class UpdateController {
    private UpdateServiceImpl updateService;
    private Feedback feedback ;
    @Autowired(required = false)
    public UpdateController(UpdateServiceImpl updateService){
        this.updateService = updateService;
    }
    @RequestMapping(value = "/version",method = RequestMethod.GET)
    @ResponseBody
    public Feedback<String> VersionCheck(@Param("versionCode") int versionCode){
        System.out.println(versionCode+"版本号");
        feedback = updateService.CheckVersion(versionCode);
        return feedback;
    }

}
