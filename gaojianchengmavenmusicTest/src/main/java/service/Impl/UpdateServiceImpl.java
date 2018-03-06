package service.Impl;

import dao.NoticeDao;
import enums.StatusEnum;
import model.Feedback;
import model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UpdateService;

import java.io.File;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UpdateServiceImpl implements UpdateService{
    private String URL = "http://120.77.38.183:8080/gaojiancheng.mavenmusic/";
    private String rootPath = "hello";
    private Feedback<Notice> feedback;

    @Autowired
    private NoticeDao noticeDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackFor = Exception.class)
    public Feedback<Notice> simpleNotice() {
        Notice notice =  noticeDao.selectLatestNotice();
        feedback = new Feedback<>();
        if (notice == null){
            feedback.setStatus(StatusEnum.METHOD_ERROR.getState());
        }else {
            feedback.setStatus(StatusEnum.OK.getState());
            feedback.setData(notice);
        }
        return feedback;
    }

//    @Override
//    @Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackFor = Exception.class)
//    public Feedback<Notice> checkVersion(int userId , int versionCode) {
//        Notice notice = new Notice();
//        feedback = new Feedback<>();
//        if (versionCode < 0){
//            feedback.setStatus(StatusEnum.VERSION_ERROR.getState());
//            return feedback;
//        }
//        String apkPath = rootPath+"saved/saveApk";
//        feedback.setStatus(StatusEnum.ALREADY_LASTEST.getState());
//        File file = new File(apkPath.substring(1));
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        File[] filesList = file.listFiles();
//        for (File file1 : filesList) {
//            //获取文件的版本号
//            int fileVersionCode = Integer.parseInt(file1.getName().replaceAll("[.][^.]+$", ""));
//            if (fileVersionCode > versionCode){
//                //有版本更新
//                String updateURLPath = URL+"saved/saveApk/"+file1.getName();
//                feedback.setStatus(StatusEnum.OK.getState());
//                notice.setNoticeMethod(Notice.VERSION_UPDATE);
//                notice.setNoticeUrl(updateURLPath);
//                feedback.setData(notice);
//            }
//        }
//        System.out.println("userId为"+userId+"versionCode为"+versionCode);
//
//        //假如现在是最新版本
//        if (feedback.getStatus() == StatusEnum.ALREADY_LASTEST.getState()){
//            //检查是否有通知
//            //获取最新的一条通知
//            Notice latestNotice = noticeDao.selectLatestNotice();
//            //检查是否已经通知了
//            if (noticeDao.checkNoticeById(userId , latestNotice.getNoticeId()) == 0){
//                //等于0就表明还没有通知到，不等于0就表明已经被通知了
//                latestNotice.setNoticeMethod(Notice.NOTICE_UPDATE);
//                feedback.setStatus(StatusEnum.OK.getState());
//                feedback.setData(latestNotice);
//                System.out.println("查看最新的通知Id"+latestNotice.getNoticeId());
//                //将消息插入数据库
//                noticeDao.informUserNotice(userId,latestNotice.getNoticeId());
//            }else {
//                feedback.setStatus(StatusEnum.ALREADY_LASTEST.getState());
//            }
//        }
//
//        return feedback;
//    }



}
