package controller;

import enums.RequestLimit;
import model.DownloadModel;
import model.Feedback;
import model.WishModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.Impl.SongServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Controller
@RequestMapping(value = "/song")
public class SongController {

    @Autowired
    private SongServiceImpl songService;
    /**
     * 获得愿望列表的控制器，采用分页查询
     * @param pageNum   第几页
     * @param pageSize  一页的数量
     */
    @RequestMapping(value = "/wantlist",method = RequestMethod.GET)
    @ResponseBody
    @RequestLimit(count = 5)
    public Feedback<List<WishModel>> getWishList(@Param("pageNum") int pageNum , @Param("pageSize") int pageSize , @Param("userId") int userId , HttpServletRequest request){
        return songService.handleWish(pageNum,pageSize,userId);
    }

    /**
     * 上传歌曲控制器
     * @param downloadModel
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Feedback<Integer> uploadSong(@RequestBody DownloadModel downloadModel){
        Feedback<Integer> feedback = songService.uploadSongService(downloadModel);
        return feedback;
    }

    /**
     * 实现愿望的控制器
     * @param wishModel 获得的参数
     * @return
     */
    @RequestMapping(value = "/achievedream",method = RequestMethod.POST)
    @ResponseBody
    public Feedback<String> achieveDream(@RequestBody WishModel wishModel){
        Feedback<String> feedback = songService.achieveSongService(wishModel);
        return feedback;
    }

    /**
     * 获得音乐区中音乐的控制器
     * @param pageNum   第几页的数据
     * @param pageSize  一页有几个数据
     * @return
     */
    @GetMapping(value = "/servermusic")
    @ResponseBody
    public Feedback<List<DownloadModel>> getMavenMusic(@Param("pageNum")int pageNum , @Param("pageSize")int pageSize , @Param("singerName")String singerName){
        return songService.getMavenMusic(pageNum,pageSize,singerName);
    }

    /**
     * 愿望请求
     * @param wishModel
     * @return
     */
    @PostMapping(value = "/desire")
    @ResponseBody
    public Feedback<String> desireMusic(@RequestBody WishModel wishModel){
        System.out.println("许愿"+wishModel);
        return songService.desireMusicService(wishModel);
    }

    @GetMapping(value = "/search")
    @ResponseBody
    public Feedback<List<DownloadModel>> searchMusic(@Param("information")String information,
                                                     @Param("pageNum") int pageNum,
                                                     @Param("pageSize") int pageSize){
        return songService.searchSongService(information,pageNum,pageSize);
    }
}
