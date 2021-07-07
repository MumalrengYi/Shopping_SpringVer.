package controller;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService {
    @Autowired
    private JavaMailSender mailSender; //Email 전송하기
    //spring-mail-context.xml 받아오기

    public void sendMail(String receiver, String content, String subject) throws Exception {
        //receiver: 받는사람 주소
        //content: 메일 내용
        //subject: 메일 제목
        MimeMessage msg = mailSender.createMimeMessage(); //MimeMeaasge라는 객체에 담아서 메일을 전송

        msg.setHeader("content-type", "text/html; charset=UTF-8");
        msg.setContent(content, "text/html; charset=UTF-8");
        msg.setSubject(subject);
        msg.setRecipient(MimeMessage.RecipientType.TO , new InternetAddress(receiver));
        mailSender.send(msg); //mailSender를 이용해서 메일을 발송함.
    }
}