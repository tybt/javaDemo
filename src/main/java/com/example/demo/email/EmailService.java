package com.example.demo.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService {
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void sendTextEmail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(emailDTO.to);
        message.setSubject(emailDTO.subject);
        message.setText(emailDTO.text);
        javaMailSender.send(message);

    }

    /**
     * 发送邮件并携带附件.
     * 请注意 from 、 to 邮件服务器是否限制邮件大小
     *
     * @param to       目标email 地址
     * @param subject  邮件主题
     * @param text     纯文本内容
     * @param filePath 附件的路径 当然你可以改写传入文件
     */
    public void sendMailWithAttachment(String to, String subject, String text, String filePath) throws MessagingException {

        File attachment = new File(filePath);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        try {
            helper.setFrom(from);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        helper.addAttachment(attachment.getName(), attachment);
        javaMailSender.send(mimeMessage);


    }


    /**
     * 发送富文本邮件.
     *
     * @param to       目标email 地址
     * @param subject  邮件主题
     * @param text     纯文本内容
     * @param filePath 附件的路径 当然你可以改写传入文件
     */
    public void sendRichMail(String to, String subject, String text, String filePath) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);

        helper.setText(text, true);
        // 图片占位写法  如果图片链接写入模板 注释下面这一行
        helper.addInline("qr", new FileSystemResource(filePath));
        javaMailSender.send(mimeMessage);

    }
}
