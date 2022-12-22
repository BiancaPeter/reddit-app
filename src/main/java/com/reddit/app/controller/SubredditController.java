package com.reddit.app.controller;

import com.reddit.app.DTO.SubredditRequestDTO;
import com.reddit.app.DTO.SubredditResponseDTO;
import com.reddit.app.model.Subreddit;
import com.reddit.app.service.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/subreddit")
public class SubredditController {
    private SubredditService subredditService;

    @Autowired
    public SubredditController(SubredditService subredditService) {
        this.subredditService = subredditService;
    }

    @PostMapping("/add")
    public ResponseEntity<Subreddit> addSubreddit(@RequestBody SubredditRequestDTO subredditRequestDTO){
        return status(HttpStatus.OK).body(subredditService.addSubreddit(subredditRequestDTO));
    }


    @GetMapping("/all")
    public ResponseEntity<List<SubredditResponseDTO>> getAllSubreddits(){
        return status(HttpStatus.OK).body(subredditService.getAllSubreddits());
    }
}
