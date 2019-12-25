package com.lg.shop.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author L
 * @version 1.0
 * @ClassName: MailUtils
 * @date: 2019/12/22 14:47
 * @since JDK 1.8
 */
public class MailUtils {
    public static void sendMail(String mailAddress,String code) throws Exception {
        Properties prop = new Properties();
        // 开启debug调试
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");

        //

        Session session = Session.getInstance(prop);
        Transport ts = session.getTransport();
        ts.connect("smtp.qq.com", "1575543408@qq.com", "fakoejcgqrnkbaej");
        //Message message = createSimpleMail(session);

        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("1575543408@qq.com"));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(
                mailAddress));
        // 邮件的标题
        message.setSubject("请激活你的账户。");
        // 邮件的文本内容
        message.setContent("<h1 style='color:red'><a href='http://192.168.22.4:9000/userActive/?code=" + code + "'>点击激活</a></h1>", "text/html;charset=UTF-8");

        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
}
