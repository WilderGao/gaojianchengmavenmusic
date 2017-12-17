package model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author:Wilder Gao
 * @time:2017/12/17
 * @Discription：
 */
public class Notice {
    public static final int NO_NOTICE = 0;
    public static final int VERSION_UPDATE = 1;
    public static final int NOTICE_UPDATE = 2;

    private int noticeId;
    @NotEmpty
    private int noticeMethod;
    @Length(max = 50 , min = 1,message = "标题长度不符合")
    private String noticeTitle;
    @Length(max = 200 , min = 1 , message = "正文长度不符合")
    private String noticeMessage;
    private String noticeUrl;

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getNoticeMethod() {
        return noticeMethod;
    }

    public void setNoticeMethod(int noticeMethod) {
        this.noticeMethod = noticeMethod;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeMessage() {
        return noticeMessage;
    }

    public void setNoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
}
