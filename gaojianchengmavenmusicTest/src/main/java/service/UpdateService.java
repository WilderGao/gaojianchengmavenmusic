package service;

import model.Feedback;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public interface UpdateService {
    Feedback<String> CheckVersion(int versionCode);
}
