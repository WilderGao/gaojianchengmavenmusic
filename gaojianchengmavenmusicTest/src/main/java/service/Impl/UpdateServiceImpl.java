package service.Impl;

import enums.StatusEnum;
import model.Feedback;
import org.springframework.stereotype.Service;
import service.UpdateService;

import java.io.File;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
@Service
public class UpdateServiceImpl implements UpdateService{
    private String URL = "http://120.77.38.183:8080/gaojiancheng.mavenmusic/";
//    private String rootPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath().replace("/WEB-INF/classes","");
    private Feedback<String> feedback;
    public Feedback<String> CheckVersion(int versionCode) {
//        feedback = new Feedback<>();
//        if (versionCode < 0){
//            feedback.setStatus(StatusEnum.VERSION_ERROR.getState());
//            return feedback;
//        }
//        String apkPath = rootPath+"saved/saveApk";
//        feedback.setStatus(StatusEnum.ALREADY_LASTEST.getState());
//        feedback.setData("");
//        File file = new File(apkPath.substring(1));
//        if (!file.exists())
//            file.mkdirs();
//        File[] filesList = file.listFiles();
//        for (File file1 : filesList) {
//            //获取文件的版本号
//            int fileVersionCode = Integer.parseInt(file1.getName().replaceAll("[.][^.]+$", ""));
//            System.out.println(versionCode+"   "+fileVersionCode);
//            if (fileVersionCode > versionCode){
//                String updateURLPath = URL+"saved/saveApk/"+file1.getName();
//                feedback.setStatus(StatusEnum.OK.getState());
//                feedback.setData(updateURLPath);
//            }
//        }
        return feedback;
    }
}
