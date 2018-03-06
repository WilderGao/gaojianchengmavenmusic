package service;

import model.Feedback;
import model.Notice;
import model.VersionUpdate;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public interface UpdateService {
    /**
     * 版本更新检查
     * @param versionCode   版本Id
     * @return
     */
    Feedback<VersionUpdate> checkVersion(int versionCode);

    Feedback<Notice> simpleNotice();
}
