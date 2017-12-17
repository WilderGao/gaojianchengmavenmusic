package service;

import model.Feedback;
import model.Notice;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public interface UpdateService {
    /**
     * 版本更新检查
     * @param userId 用户Id
     * @param versionCode   版本Id
     * @return
     */
    Feedback<Notice> checkVersion(int userId , int versionCode);
}
