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
    Feedback CloudFile(String rootPath , DownloadModel downloadModel) throws IOException;

    /**
     * 获取这个用户的下载记录
     * @param pageNum　分页参数
     * @param pageSize
     * @param customerId
     * @return
     */
    Feedback GetSongsList(int pageNum , int pageSize , long customerId);
}
