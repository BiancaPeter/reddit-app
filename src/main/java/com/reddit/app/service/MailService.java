package com.reddit.app.service;

import com.reddit.app.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private JavaMailSender emailSender;

    @Autowired
    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendCommentMessage(String recipientMail, Comment comment) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("biancasender60@gmail.com");
        helper.setTo(recipientMail);

        helper.setSubject("New comment to " + comment.getPost().getPostName());
        helper.setText(comment.getUser().getUsername() + " add a comment to your post: "  + comment.getText());
        emailSender.send(message);
    }
}
