package service.Impl;

import com.github.pagehelper.PageHelper;
import dao.InsertSongDao;
import enums.StatusEnum;
import model.DownloadModel;
import model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DownloadService;
import utils.PatternUtils;
import utils.QiniuUtils;

import java.io.*;
import java.util.List;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DownloadServiceImpl implements DownloadService {
    private InsertSongDao insertSongDao;
    Feedback<Integer> feedback = new Feedback<>();
    private static Logger LOGGER = LoggerFactory.getLogger(DownloadServiceImpl.class);

    @Autowired(required = false)
    public DownloadServiceImpl(InsertSongDao insertSongDao){
        this.insertSongDao = insertSongDao;
    }


    @Override
    public Feedback cloudFile(DownloadModel downloadModel) throws IOException {
        String singerName = downloadModel.getSingerName();
        System.out.println(singerName);
        if (downloadModel== null || downloadModel.getPlayUrl() == null ) {
            feedback.setStatus(StatusEnum.URL_NULL.getState());
        }else {
            //分别得到图片和歌曲的Url
            String imgPath = downloadModel.getImgUrl();
            String playPath = downloadModel.getPlayUrl();
            //将歌曲上传到七牛云并且获得存放在七牛的url
            String uploadPlayUrl = QiniuUtils.uploadFileToQiniu(downloadModel.getSongName(),playPath);
            if (uploadPlayUrl != null) {
                downloadModel.setPlayUrl(uploadPlayUrl);
                String singerPic = PatternUtils.getSingerPicUrl(singerName);
                downloadModel.setSingerUrl(singerPic);
            }
            //最后将路径保存到数据库
            try {
                int checkExist = 0;
                if (imgPath!=null && playPath!=null) {
                    List<DownloadModel> downloadModels = insertSongDao.getSongs(downloadModel.getCustomerId());
                     //遍历查找这个用户是否已经下载了这首歌
                    for (DownloadModel model : downloadModels) {
                        if (model.getSingerName().equals(downloadModel.getSingerName()) && model.getSongName().equals(downloadModel.getSongName()))
                        { checkExist = 1;}
                    }
                    if (checkExist != 1)
                    { insertSongDao.insertSongCloud(downloadModel);}
                }
            }catch (Exception e){
                System.out.println("插入数据库发生错误");
                e.printStackTrace();
                feedback.setStatus(StatusEnum.INSERT_SQL_ERROR.getState());
                return feedback;
            }
            feedback.setStatus(StatusEnum.OK.getState());
        }
        return feedback;
    }

    @Override
    public Feedback GetSongsList(int pageNum , int pageSize , long customerId) {
        Feedback<List<DownloadModel>> feedback = new Feedback<>();
        if (pageNum <= 0 || pageSize <= 0) {
            feedback.setStatus(StatusEnum.METHOD_ERROR.getState());
            return feedback;
        }else {
            //开始分页
            PageHelper.startPage(pageNum, pageSize);
            List<DownloadModel> downloadModel = insertSongDao.getSongs(customerId);
            feedback.setData(downloadModel);
            feedback.setStatus(StatusEnum.OK.getState());
            return feedback;
        }
    }
}
