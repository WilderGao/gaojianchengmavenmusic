package controller;

import enums.StatusEnum;
import model.DownloadModel;
import model.Feedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.Impl.DownloadServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Controller
@RequestMapping(value = "/download")
public class DownloadController {
    private DownloadServiceImpl downloadService;

    @Autowired(required = false)
    public DownloadController(DownloadServiceImpl downloadService){
        this.downloadService = downloadService;
    }

    @RequestMapping(value = "/songs",method = RequestMethod.POST)
    @ResponseBody
    public Feedback SaveSongs(@RequestBody  DownloadModel downloadModel ){
        System.out.println(downloadModel+"\n");
        //获取WEB-INF路径
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath().replace("/WEB-INF/classes","");
        Feedback feedback = new Feedback();
        try {
             feedback = downloadService.CloudFile(rootPath,downloadModel);
             return feedback;
        } catch (IOException e) {
            e.printStackTrace();
            feedback.setStatus(StatusEnum.UPLOAD_CLOUD_ERROR.getState());
            return feedback;
        }
    }

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    public Feedback<List<DownloadModel>> GetCustomerSongs(@Param("pageNum") int pageNum , @Param("pageSize") int pageSize , @Param("userId") int userId){
        Feedback<List<DownloadModel>> feedback = downloadService.GetSongsList(pageNum , pageSize , (long)userId);
        return feedback;
    }
}
