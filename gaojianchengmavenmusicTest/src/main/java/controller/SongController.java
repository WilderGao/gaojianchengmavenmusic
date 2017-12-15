package controller;

import model.DownloadModel;
import model.Feedback;
import model.WishModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.Impl.SongServiceImpl;

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
     * 获得歌曲列表的控制器，采用分页查询
     * @param pageNum   第几页
     * @param pageSize  一页的数量
     */
    @RequestMapping(value = "/wantlist",method = RequestMethod.GET)
    @ResponseBody
    public Feedback<List<WishModel>> getWishList(int pageNum , int pageSize){
        return songService.handleWish(pageNum,pageSize);
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
        System.out.println("pageNum:"+pageNum+"pageSize:"+pageSize+"singerName:"+singerName);
        return songService.getMavenMusic(pageNum,pageSize,singerName);
    }
}
