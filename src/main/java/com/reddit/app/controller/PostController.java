package com.reddit.app.controller;

import com.reddit.app.DTO.PostRequestDTO;
import com.reddit.app.DTO.PostResponseDTO;
import com.reddit.app.model.Post;
import com.reddit.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@RequestBody PostRequestDTO postRequestDTO){
        return status(HttpStatus.OK).body(postService.addPost(postRequestDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostResponseDTO>> allPosts(){
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostBy(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPostBy(id));
    }

    @GetMapping("/subreddit/{id}")
    public ResponseEntity<List<PostResponseDTO>> getPostsBySubreddit(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPostsBySubreddit(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByUser(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPostsByUser(id));
    }

}
