/*package com.biang.www.util;





import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

*//**
 * @author dell
 *//*
public class EmailSender {
    // 指明邮件的发件人
    private static String mailFrom = null;
    // 指明邮件的发件人登陆密码
    private static String mailFromPassword = null;
    // 指明邮件的收件人
    private static String mailTo = null;
    // 邮件的标题
    private static String mailTittle = null;
    // 邮件的文本内容
    private static String mailText = null;
    // 邮件的服务器域名
    private static String mail_host = null;

    public static void text() throws Exception {
        mailFrom = "2947296752@qq.com";
        mailFromPassword = "BIANG0216";
        mailTo = "921097712@qq.com";
        mailTittle = "发送成功！";
        mailText = "这是一个简单的邮件";
        mail_host = "qq.com";

        Properties prop = new Properties();
        prop.setProperty("mail.host", mail_host);
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");

        // 使用JavaMail发送邮件的5个步骤

        // 1、创建session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport();
        // 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect(mail_host, mailFrom, mailFromPassword);
        // 4、创建邮件
        Message message = createSimpleMail(session, mailFrom, mailTo, mailTittle, mailText);
        // 5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    */
/**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     *//*
    public static MimeMessage createSimpleMail(Session session, String mailfrom, String mailTo, String mailTittle,
                                               String mailText) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(mailfrom));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
        // 邮件的标题
        message.setSubject(mailTittle);
        // 邮件的文本内容
        message.setContent(mailText, "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;

    }
}*/

package com.biang.www.util;

import com.biang.www.po.User;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class EmailSender {
    public EmailSender(){};
    public EmailSender(String mailFrom, String mailFromPassword, String mailTo, String mailTittle, String mailText, String mailHost) {
        this.mailFrom = mailFrom;
        this.mailFromPassword = mailFromPassword;
        this.mailTo = mailTo;
        this.mailTittle = mailTittle;
        this.mailText = mailText;
        this.mailHost = mailHost;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailFromPassword() {
        return mailFromPassword;
    }

    public void setMailFromPassword(String mailFromPassword) {
        this.mailFromPassword = mailFromPassword;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailTittle() {
        return mailTittle;
    }

    public void setMailTittle(String mailTittle) {
        this.mailTittle = mailTittle;
    }

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    // 指明邮件的发件人
    private String mailFrom = null;
    // 指明邮件的发件人登陆密码
    private String mailFromPassword = null;
    // 指明邮件的收件人
    private String mailTo = null;
    // 邮件的标题
    private String mailTittle = null;
    // 邮件的文本内容
    private String mailText = null;
    // 邮件的服务器域名
    private String mailHost = null;
    public void textInitialization(){
        mailFrom="2947296752@qq.com";
        mailFromPassword="lxakfuebeogoddif";
        mailTo="921097712@qq.com";
        mailTittle="邮箱发送测试";
        mailText="芜湖起飞";
        mailHost="smtp.qq.com";
    }
    public void send() throws MessagingException, GeneralSecurityException {
        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host",mailHost);

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom,mailFromPassword);
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect(mailHost,mailFrom,mailFromPassword);

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(mailFrom));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(mailTo));

        //邮件标题
        mimeMessage.setSubject(mailTittle);

        //邮件内容
        mimeMessage.setContent(mailText,"text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
    }

    public void Initialization(User forgetPasswordUser,String checkCode){
        mailFrom="2947296752@qq.com";
        mailFromPassword="lxakfuebeogoddif";
        mailTo=forgetPasswordUser.getEmail();
        mailTittle="成果交易系统找回密码";
        mailText=forgetPasswordUser.getUserName()+"，您好！<br/>" +
                "您找回密码的帐户 "+forgetPasswordUser.getUserName()+" 所需的验证码为：<br/>" +
                checkCode+"<br/>" +
                "若非您本人操作则请忽略<br/>" +
                "biang<br/>" +
                "<a href=\"http://localhost:8080/ChengGuo_war_exploded/\">成果交易系统主页</a>";
        mailHost="smtp.qq.com";
    }
}