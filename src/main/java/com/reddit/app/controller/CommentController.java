package com.reddit.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.reddit.app.DTO.CommentRequestDTO;
import com.reddit.app.DTO.CommentResponseDTO;
import com.reddit.app.model.Comment;
import com.reddit.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequestDTO commentRequestDTO) throws JsonProcessingException, MessagingException {
        return status(HttpStatus.OK).body(commentService.addComment(commentRequestDTO));
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(commentService.getCommentsByPost(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByUser(@PathVariable Long id) {
        return status(HttpStatus.OK).body(commentService.getCommentsByUser(id));
    }


    //TODO: delete a comment
}
