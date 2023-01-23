package com.reddit.app.service;

import com.reddit.app.DTO.PostRequestDTO;
import com.reddit.app.DTO.PostResponseDTO;
import com.reddit.app.mapper.PostMapper;
import com.reddit.app.model.Post;
import com.reddit.app.model.Subreddit;
import com.reddit.app.model.User;
import com.reddit.app.repository.PostRepository;
import com.reddit.app.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private SubredditService subredditService;

    private UserService userService;

    private VoteRepository voteRepository;

    private PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper, PostRepository postRepository, SubredditService subredditService, UserService userService, VoteRepository voteRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
        this.subredditService = subredditService;
        this.userService = userService;
        this.voteRepository = voteRepository;
    }

    public Post addPost(PostRequestDTO postRequestDTO) {
        return postRepository.save(postMapper.mapDtoToPost(postRequestDTO));
    }

    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> postMapper.mapPostToDto(post))
                .collect(Collectors.toList());
    }

    public PostResponseDTO getPostBy(Long id) {
        Post foundPost = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "post not found"));
        //return constructNewPostResponseDTO(foundPost);
        return  postMapper.mapPostToDto(foundPost);
    }

    public List<PostResponseDTO> getPostsBySubreddit(Long id) {
        Subreddit foundSubreddit = subredditService.findSubreddit(id);
        return postRepository.findBySubreddit(foundSubreddit).stream()
                .map(post -> postMapper.mapPostToDto(post))
                .collect(Collectors.toList());
    }

    public List<PostResponseDTO> getPostsByUser(Long id) {
        User foundUser = userService.findUser(id);
        return postRepository.findByUser(foundUser).stream()
                .map(post -> postMapper.mapPostToDto(post))
                .collect(Collectors.toList());
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the post was not found"));
    }

    public Post update(Post post) {
        return postRepository.save(post);
    }
}
