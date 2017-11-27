package service.Impl;

import dao.InsertSongDao;
import enums.StatusEnum;
import model.DownloadModel;
import model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DownloadService;
import utils.FileUtils;
import utils.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
@Service
public class DownloadServiceImpl implements DownloadService {
    private InsertSongDao insertSongDao;
    Feedback<Integer> feedback = new Feedback<>();
    private static Logger LOGGER = LoggerFactory.getLogger(DownloadServiceImpl.class);

    @Autowired(required = false)
    public DownloadServiceImpl(InsertSongDao insertSongDao){
        this.insertSongDao = insertSongDao;
    }


    @Override
    public Feedback CloudFile(String rootPath, DownloadModel downloadModel) throws IOException {
        if (rootPath == null || downloadModel== null || downloadModel.getPlayUrl() == null ) {
            feedback.setStatus(StatusEnum.URL_NULL.getState());
        }else {

            String ImgPath = null;
            String PlayPath = null;
            //下载的数组
            byte[] bytes = new byte[1024];
            int check;
            //对图片进行处理
            if (downloadModel.getImgUrl()!=null && downloadModel.getPlayUrl()!=null) {
                ImgPath = FileUtils.getImgPath(rootPath) + downloadModel.getImgUrl().substring(downloadModel.getImgUrl().lastIndexOf("/"));
                PlayPath = FileUtils.getPlayPath(rootPath) + downloadModel.getPlayUrl().substring(downloadModel.getPlayUrl().lastIndexOf("/"));
                File PlayFile = new File(PlayPath);
                File ImgFile = new File(ImgPath);

                if (!ImgFile.exists()) {
                    try {
                        ImgFile.createNewFile();
                    } catch (IOException e) {
                        LOGGER.error("创建文件失败");
                        throw new RuntimeException(e);
                    }
                }
                if (!PlayFile.exists()) {
                    try {
                        PlayFile.createNewFile();
                    } catch (IOException e) {
                        LOGGER.error("创建文件失败");
                        throw new RuntimeException(e);
                    }
                }

                //从URL中获取下载图片资源
                BufferedInputStream inputStream = new BufferedInputStream(new URL(downloadModel.getImgUrl()).openConnection().getInputStream());
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(ImgFile));
                while ((check = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, check);
                }
                inputStream.close();
                outputStream.close();

                //下载音乐资源
                BufferedInputStream musicInputStream = new BufferedInputStream(new URL(downloadModel.getPlayUrl()).openConnection().getInputStream());
                BufferedOutputStream musicOutputStream = new BufferedOutputStream(new FileOutputStream(PlayFile));
                while ((check = musicInputStream.read(bytes))!=-1){
                    musicOutputStream.write(bytes,0,check);
                }
                musicInputStream.close();
                musicOutputStream.close();
            }


            //最后将路径保存到数据库
            try {
                int checkExist = 0;
                LOGGER.info(downloadModel.getSingerName()+"\n"+downloadModel.getSongName());
                if (ImgPath!=null && PlayPath!=null) {
                    List<DownloadModel> downloadModels = insertSongDao.GetSongs(downloadModel.getUserId());
                    for (DownloadModel model : downloadModels) {
                        if (model.getSingerName().equals(downloadModel.getSingerName()) && model.getSongName().equals(downloadModel.getSongName()))
                        { checkExist = 1;}
                    }
                    if (checkExist != 1)
                    { insertSongDao.InsertSongCloud(downloadModel.getUserId(), ImgPath, downloadModel.getSongName(),
                                downloadModel.getSingerName(), PlayPath);}
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
    public Feedback GetSongsList(long customerId) {
        List<DownloadModel> downloadModel = insertSongDao.GetSongs(customerId);
        for (DownloadModel model : downloadModel) {
            model.setImgUrl(StringUtils.ImgString(model.getImgUrl()));
            model.setPlayUrl(StringUtils.PlayString(model.getPlayUrl()));
        }
        Feedback<List<DownloadModel>> feedback = new Feedback<>();
        feedback.setData(downloadModel);
        feedback.setStatus(StatusEnum.OK.getState());
        return feedback;
    }
}
