package dao;

import model.Notice;
import model.VersionUpdate;
import org.apache.ibatis.annotations.Param;

/**
 * @author:Wilder Gao
 * @time:2017/12/17
 * @Discription：通知dao接口
 */
public interface NoticeDao {
    /**
     * 找出最新的一条通知信息
     * @return
     */
    Notice selectLatestNotice();

    /**
     * 检查某个用户是否已经被通知到
     * @param userId
     * @param noticeId
     * @return
     */
    int checkNoticeById(@Param("customerId")int userId , @Param("noticeId") int noticeId);

    /**
     * 插入通知列表
     */
    void informUserNotice(@Param("customerId") int customerId , @Param("noticeId") int noticeId);

    /**
     * 获取最新的一条更新信息
     * @return
     */
    VersionUpdate selectLatestVersion();
}
