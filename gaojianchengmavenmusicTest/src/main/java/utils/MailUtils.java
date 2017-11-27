package utils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public final class MailUtils {
    private static String myEmailAccount = "845956752@qq.com";
    private static String myEmailPassword = "zfpexnxfsauibehh";

    //SMTP服务器地址
    private static String myEmailSMTPHost = "smtp.qq.com";

    /**
     * 发送验证邮件
     * @param receiveMailAccount
     * @return
     */
    public static boolean sent(String receiveMailAccount , String emailMessage) throws UnsupportedEncodingException, MessagingException {
        //创建参数配置
        Properties props = new Properties();// 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");

        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        //根据配置创建会话对象，用于和邮件服务器的交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        //创建一封邮件
        MimeMessage mimeMessage = creatMimeMessage(session , myEmailAccount , receiveMailAccount , emailMessage);
        if (mimeMessage == null) {
            throw new RuntimeException("创建邮件失败");
        }else {
            Transport transport = session.getTransport();
            transport.connect(myEmailAccount,myEmailPassword);

            //发送邮件
            transport.sendMessage(mimeMessage , mimeMessage.getAllRecipients());
            return true;

        }

    }

    public static MimeMessage creatMimeMessage(Session session , String sendMail , String receiveMail , String emailMessage) throws UnsupportedEncodingException, MessagingException {
        //1、创建一封邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        String mailSender = "来自Maven音乐团队";
        String mailReceiver = "尊敬的用户您好!";
        //2、发件人
        mimeMessage.setFrom(new InternetAddress(sendMail ,mailSender,"utf-8" ));
        //收件人
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress[]{new InternetAddress(receiveMail, mailReceiver, "utf-8")});
        //邮件主题
        mimeMessage.setSubject("用户验证","utf-8");
        //邮件正文
        mimeMessage.setContent(emailMessage,"text/html;charset=UTF-8");

        mimeMessage.saveChanges();

        return mimeMessage;
    }

}
