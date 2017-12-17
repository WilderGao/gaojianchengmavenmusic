package service.Impl;

import com.github.pagehelper.PageHelper;
import dao.InsertSongDao;
import dao.SongDao;
import enums.StatusEnum;
import model.DownloadModel;
import model.Feedback;
import model.WishModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.SongService;
import utils.PatternUtils;

import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Service
//设置事务
@Transactional(rollbackFor = Exception.class)
public class SongServiceImpl implements SongService {
    /**
     * 日志打印
     */
    private Logger LOGGER = LoggerFactory.getLogger(SongServiceImpl.class);

    @Autowired
    private SongDao songDao;
    @Autowired
    private InsertSongDao insertSongDao;

    @Override
    public Feedback<List<WishModel>> handleWish(int pageNum, int pageSize , int userId) {
        Feedback<List<WishModel>> feedback = new Feedback<>();
        //判断查询的页码错误与否
        if (pageNum <= 0 || pageSize <= 0){
            feedback.setStatus(StatusEnum.PAGE_NUM_ERROR.getState());
            return feedback;
        }else {
            PageHelper.startPage(pageNum,pageSize);
            List<WishModel> wishModelList = songDao.getWishList(userId);
            feedback.setStatus(StatusEnum.OK.getState());
            feedback.setData(wishModelList);
            return feedback;
        }
    }

    @Override
    public Feedback<Integer> uploadSongService(DownloadModel downloadModel) {
        Feedback<Integer> feedback = new Feedback<>();
        if (null == downloadModel || downloadModel.getCustomerId() == 0 || null == downloadModel.getPlayUrl()){
            //获得内容为空，没有对应用户的Id，歌曲对应的url为空的时候都判断为错误
            feedback.setStatus(StatusEnum.METHOD_ERROR.getState());
            return feedback;
        }else {
            songDao.uploadSong(downloadModel);
            feedback.setStatus(StatusEnum.OK.getState());
            return feedback;
        }
    }

    @Override
    public Feedback<String> achieveSongService(WishModel wishModel) {
        Feedback<String> feedback = new Feedback<>();
        if (wishModel == null || wishModel.getWishId() <= 0 || wishModel.getSongURL() == null){
            feedback.setStatus(StatusEnum.METHOD_ERROR.getState());
        }else {
            try {
                songDao.updateWishState(wishModel);
                //这里只要通过Id来找愿望并且更新下载清单
                DownloadModel downloadModel = songDao.selectWish(wishModel.getWishId(),null,null);
                downloadModel.setPlayUrl(wishModel.getSongURL());
                downloadModel.setImgUrl(PatternUtils.getSingerPicUrl(downloadModel.getSingerName()));
                insertSongDao.insertSongCloud(downloadModel);
                //正常情况
                feedback.setStatus(StatusEnum.OK.getState());
                return feedback;
            }catch (Exception e){
                LOGGER.error("========数据库操作出现异常，事务回滚==========");
                feedback.setStatus(StatusEnum.INSERT_SQL_ERROR.getState());
                return feedback;
            }
        }
        return feedback;
    }


    @Override
    public Feedback<List<DownloadModel>> getMavenMusic(int pageNum, int pageSize , String singerName) {
        Feedback<List<DownloadModel>> feedback = new Feedback<>();
        if (pageNum <= 0 || pageSize <= 0) {
            feedback.setStatus(StatusEnum.PAGE_NUM_ERROR.getState());
            return feedback;
        } else {
            //开启分页
            PageHelper.startPage(pageNum, pageSize);
            try {
                if (null == singerName) {
                    //名称为空的时候说明要获得的是歌手的名称
                    //获得所有歌手的信息集合
                    LOGGER.info("========要获得歌手信息=======");
                    List<DownloadModel> singerList = insertSongDao.selectMavenSinger();
                    if (null == singerList) {
                        feedback.setStatus(StatusEnum.INSERT_SQL_ERROR.getState());
                    } else {
                        feedback.setStatus(StatusEnum.OK.getState());
                        feedback.setData(singerList);
                    }
                    return feedback;
                } else {
                    //查询数据库
                    List<DownloadModel> downloadModelList = insertSongDao.getMavenMusicSongs(singerName);
                    if (null == downloadModelList) {
                        feedback.setStatus(StatusEnum.INSERT_SQL_ERROR.getState());
                        return feedback;
                    } else {
                        feedback.setStatus(StatusEnum.OK.getState());
                        feedback.setData(downloadModelList);
                        return feedback;
                    }
                }
            }catch(Exception e){
                LOGGER.error("======使用数据库出现错误======");
                feedback.setStatus(StatusEnum.INSERT_SQL_ERROR.getState());
                e.printStackTrace();
                return feedback;
            }
        }
    }

    @Override
    public Feedback<List<WishModel>> selectUserSongService(int userId, int pageNum, int pageSize) {
        Feedback<List<WishModel>> feedback = new Feedback<>();
        if (userId <= 0 || pageNum <= 0 || pageSize <= 0){
            feedback.setStatus(StatusEnum.METHOD_ERROR.getState());
            return feedback;
        }
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<WishModel> wishModels = songDao.selectWishAboutUser(userId);
            feedback.setStatus(StatusEnum.OK.getState());
            feedback.setData(wishModels);
            return feedback;
        }catch (Exception e){
            LOGGER.error("=========使用数据库出现错误了========");
            feedback.setStatus(StatusEnum.INSERT_SQL_ERROR.getState());
            return feedback;
        }
    }

    @Override
    public Feedback<String> desireMusicService(WishModel wishModel) {
        Feedback<String> feedback = new Feedback<>();
        if (null == wishModel || wishModel.getSingerName() == null || wishModel.getSongName() == null){
            //参数出现错误
            feedback.setStatus(StatusEnum.METHOD_ERROR.getState());
            return feedback;
        }else {
            DownloadModel downloadModel = songDao.selectWish(-1,wishModel.getSongName(),wishModel.getSingerName());
            if (downloadModel != null){
                //不等于空证明这条愿望已经存在了
                feedback.setStatus(StatusEnum.DESIRE_EXIST.getState());
                return feedback;
            }else {
                try {
                    songDao.insertWish(wishModel);
                    feedback.setStatus(StatusEnum.OK.getState());
                    return feedback;
                }catch (Exception e){
                    LOGGER.error("=======插入数据库出现错误======");
                    e.printStackTrace();
                    feedback.setStatus(StatusEnum.INSERT_SQL_ERROR.getState());
                    return feedback;
                }
            }
        }

    }


}
