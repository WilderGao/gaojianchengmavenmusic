package service;

import model.DownloadModel;
import model.Feedback;

import java.io.IOException;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public interface DownloadService {
    /**
     * 将文件从酷狗直接上传到七牛云
     * @param downloadModel
     * @return
     * @throws IOException
     */
    Feedback cloudFile(DownloadModel downloadModel) throws IOException;

    /**
     * 获取这个用户的下载记录
     * @param pageNum　分页参数
     * @param pageSize
     * @param customerId
     * @return
     */
    Feedback GetSongsList(int pageNum , int pageSize , long customerId);
}
