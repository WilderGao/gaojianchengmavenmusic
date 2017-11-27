package service;

import model.DownloadModel;
import model.Feedback;

import java.io.IOException;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public interface DownloadService {
    Feedback CloudFile(String rootPath , DownloadModel downloadModel) throws IOException;
    Feedback GetSongsList(long customerId);
}
