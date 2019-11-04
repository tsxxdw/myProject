package cn.tsxxdw.service.mymail;

import cn.tsxxdw.vo.ResultVo;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by admin on 2018/6/24.
 */
public class MyMailUtils {
    public static void sendMail(String fromAdress, String fromPassword, String toAdress, String theme, String content) throws Exception {


        //设置发送邮件的主机  smtp.qq.com

        String host = "smtp.qq.com";

        //1.创建连接对象，连接到邮箱服务器

        Properties props = System.getProperties();

        //Properties 用来设置服务器地址，主机名 。。 可以省略

        //设置邮件服务器

        props.setProperty("mail.smtp.host", host);

        props.put("mail.smtp.auth", "true");

        //SSL加密

        MailSSLSocketFactory sf = new MailSSLSocketFactory();

        sf.setTrustAllHosts(true);

        props.put("mail.smtp.ssl.enable", "true");

        props.put("mail.smtp.ssl.socketFactory", sf);

        //props：用来设置服务器地址，主机名；Authenticator：认证信息

        Session session = Session.getDefaultInstance(props, new Authenticator() {

            @Override

            //通过密码认证信息

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(fromAdress, fromPassword);

            }

        });

        try {

            Message message = new MimeMessage(session);

            //2.1设置发件人：

            message.setFrom(new InternetAddress(fromAdress));

            //2.2设置收件人 这个TO就是收件人

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAdress));

            //2.3邮件的主题

            message.setSubject(theme);

            //2.4设置邮件的正文 第一个参数是邮件的正文内容 第二个参数是：是文本还是html的连接

            message.setContent(content, "text/html;charset=UTF-8");

            //3.发送一封激活邮件

            Transport.send(message);


        } catch (MessagingException mex) {

            mex.printStackTrace();

        }


    }


    public static void main(String[] args) throws MessagingException {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
