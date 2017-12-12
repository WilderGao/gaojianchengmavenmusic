package controller;

import model.DownloadModel;
import model.Feedback;
import model.WishModel;
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

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Feedback<Integer> uploadSong(@RequestBody DownloadModel downloadModel){
        System.out.println("downloadModel"+downloadModel);
        Feedback<Integer> feedback = songService.uploadSongService(downloadModel);
        return feedback;
    }
}
