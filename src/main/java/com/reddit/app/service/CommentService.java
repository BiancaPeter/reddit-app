package com.reddit.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reddit.app.DTO.CommentRequestDTO;
import com.reddit.app.DTO.CommentResponseDTO;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    private PostService postService;

    private UserService userService;

    private RestTemplate restTemplate;

    private static final String CONTAINS_PROFANITY_TEXT_URL = "https://www.purgomalum.com/service/containsprofanity?text={textToCheck}";

    private MailService mailService;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostService postService, UserService userService, RestTemplate restTemplate, MailService mailService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.mailService = mailService;
    }

    public Comment addComment(CommentRequestDTO commentRequestDTO) throws JsonProcessingException, MessagingException {
        Post foundPost = postService.findPost(commentRequestDTO.getPostId());
        //TODO: response ar putea fi direct valoare booleana? si nu JsonNode?
        //am mai incercat ca in metoda makeAPICall sa returnez un String aplicand response.toString, dar valoarea
        //retunata de tip string continea mai multe informatii printre care si valoarea de adevar true/false
        //si ar fi fost necesar sa fac un split pt a-mi extrage informatia true/false
        //(in acest caz renuntam la ObjectMapper objectMapper = new ObjectMapper();)
        //
        //ce se poate imbunatati la codul actual????
        JsonNode response = getResponseBodyJson(CONTAINS_PROFANITY_TEXT_URL, commentRequestDTO.getText());
        if (response.asBoolean()) {
            throw new ResponseStatusException(HttpStatus.LOCKED, "the comment contains profanity words");
        }

        Comment comment = new Comment();
        comment.setText(commentRequestDTO.getText());
        comment.setPost(foundPost);
        comment.setCreatedDate(LocalDateTime.now());
        comment.setUser(userService.findLoggedInUser());

        mailService.sendCommentMessage(comment.getPost().getUser().getEmail(), comment);

        return commentRepository.save(comment);
    }

    public JsonNode getResponseBodyJson(String requestBaseUrl, String textToCheck) throws JsonProcessingException {
        URI url = new UriTemplate(requestBaseUrl).expand(textToCheck);
        return makeAPICall(url);
    }

    private JsonNode makeAPICall(URI url) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.getBody());
    }

    public List<CommentResponseDTO> getCommentsByPost(Long id) {
        Post foundPost = postService.findPost(id);
        return transformCommentListDBToCommentResponseDTOList(foundPost.getCommentList());
    }


    public List<CommentResponseDTO> transformCommentListDBToCommentResponseDTOList(List<Comment>commentList) {
        List<CommentResponseDTO> commentResponseDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentResponseDTO newCommentResponseDTO = constructNewCommentResponseDTO(comment);
            commentResponseDTOList.add(newCommentResponseDTO);
        }
        return commentResponseDTOList;
    }

    public CommentResponseDTO constructNewCommentResponseDTO(Comment comment){
        CommentResponseDTO newCommentResponseDTO = new CommentResponseDTO();
        newCommentResponseDTO.setId(comment.getId());
        newCommentResponseDTO.setPostId(comment.getPost().getId());
        newCommentResponseDTO.setCreatedDate(comment.getCreatedDate());
        newCommentResponseDTO.setText(comment.getText());
        newCommentResponseDTO.setUsername(comment.getUser().getUsername());
        return newCommentResponseDTO;
    }

    public List<CommentResponseDTO> getCommentsByUser(Long id) {
        User foundUser = userService.findUser(id);
        return transformCommentListDBToCommentResponseDTOList(foundUser.getCommentList());
    }

}

