package com.reddit.app.service;

import com.reddit.app.model.Comment;
import com.reddit.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private JavaMailSender emailSender;

    private PostRepository postRepository;
    private UserService userService;

    @Autowired
    public MailService(JavaMailSender emailSender, PostRepository postRepository, UserService userService) {
        this.emailSender = emailSender;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void sendCommentMessage(String recipientMail, String text, Long postId) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("biancasender60@gmail.com");
        helper.setTo(recipientMail);

        helper.setSubject("New comment to " + postRepository.findById(postId));
        helper.setText(userService.findLoggedInUser().getUsername() + " add a comment to your post: "  + text);
        emailSender.send(message);
    }
}
