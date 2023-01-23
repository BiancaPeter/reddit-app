package com.reddit.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.reddit.app.DTO.CommentRequestDTO;
import com.reddit.app.DTO.CommentResponseDTO;
import com.reddit.app.mapper.CommentMapper;
import com.reddit.app.model.Comment;
import com.reddit.app.model.Post;
import com.reddit.app.model.User;
import com.reddit.app.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriTemplate;

import javax.mail.MessagingException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentMapper commentMapper;
    private CommentRepository commentRepository;

    private PostService postService;

    private UserService userService;

    private RestTemplate restTemplate;

    private static final String CONTAINS_PROFANITY_TEXT_URL = "https://www.purgomalum.com/service/containsprofanity?text={textToCheck}";

    private MailService mailService;

    @Autowired
    public CommentService(CommentMapper commentMapper, CommentRepository commentRepository, PostService postService, UserService userService, RestTemplate restTemplate, MailService mailService) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.mailService = mailService;
    }

    public Comment addComment(CommentRequestDTO commentRequestDTO) throws JsonProcessingException, MessagingException {
        String response = getResponseBodyJson(CONTAINS_PROFANITY_TEXT_URL, commentRequestDTO.getText());
        if (Boolean.parseBoolean(response)) {
            throw new ResponseStatusException(HttpStatus.LOCKED, "the comment contains profanity words");
        }

        mailService.sendCommentMessage(postService.findPost(commentRequestDTO.getPostId()).getUser().getEmail(), commentRequestDTO.getText(), commentRequestDTO.getPostId());

        return commentRepository.save(commentMapper.mapDtoToComment(commentRequestDTO));
    }

    public String getResponseBodyJson(String requestBaseUrl, String textToCheck) throws JsonProcessingException {
        URI url = new UriTemplate(requestBaseUrl).expand(textToCheck);
        return makeAPICall(url);
    }

    private String makeAPICall(URI url) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public List<CommentResponseDTO> getCommentsByPost(Long id) {
        Post foundPost = postService.findPost(id);
        return foundPost.getCommentList().stream()
                .map(comment -> commentMapper.mapCommentToDto(comment))
                .collect(Collectors.toList());
    }

    public List<CommentResponseDTO> getCommentsByUser(Long id) {
        User foundUser = userService.findUser(id);
        return foundUser.getCommentList().stream()
                .map(comment -> commentMapper.mapCommentToDto(comment))
                .collect(Collectors.toList());
    }

}

