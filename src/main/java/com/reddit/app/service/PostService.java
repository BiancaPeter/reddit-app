package com.reddit.app.service;

import com.reddit.app.DTO.PostRequestDTO;
import com.reddit.app.DTO.PostResponseDTO;
import com.reddit.app.model.Post;
import com.reddit.app.model.Subreddit;
import com.reddit.app.model.User;
import com.reddit.app.model.Vote;
import com.reddit.app.repository.PostRepository;
import com.reddit.app.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private SubredditService subredditService;

    private UserService userService;

    private VoteRepository voteRepository;

    @Autowired
    public PostService(PostRepository postRepository, SubredditService subredditService, UserService userService, VoteRepository voteRepository) {
        this.postRepository = postRepository;
        this.subredditService = subredditService;
        this.userService = userService;
        this.voteRepository = voteRepository;
    }

    public Post addPost(PostRequestDTO postRequestDTO) {
        Subreddit foundSubreddit = subredditService.findSubreddit(postRequestDTO.getSubredditId());
        Post post = new Post();
        post.setPostName(postRequestDTO.getPostName());
        post.setDescription(postRequestDTO.getDescription());
        post.setCreatedDate(LocalDateTime.now());
        post.setSubreddit(foundSubreddit);
        post.setUser(userService.findLoggedInUser());
        post.setVoteCount(0);
        return postRepository.save(post);
    }

    public List<PostResponseDTO> allPosts() {
        List<Post> postListDB = postRepository.findAll();
        return transformPostListDBToPostResponseDTOList(postListDB);
    }

    private List<PostResponseDTO> transformPostListDBToPostResponseDTOList(List<Post> postListDB) {
        List<PostResponseDTO> postResponseDTOList = new ArrayList<>();
        for (Post post : postListDB) {
            PostResponseDTO newPostResponseDTO = constructNewPostResponseDTO(post);
            postResponseDTOList.add(newPostResponseDTO);
        }
        return postResponseDTOList;
    }

    private PostResponseDTO constructNewPostResponseDTO(Post post) {
        PostResponseDTO newPostResponseDTO = new PostResponseDTO();
        newPostResponseDTO.setId(post.getId());
        newPostResponseDTO.setPostName(post.getPostName());
        newPostResponseDTO.setDescription(post.getDescription());
        newPostResponseDTO.setUserName(post.getUser().getUsername());
        newPostResponseDTO.setSubredditName(post.getSubreddit().getName());
        newPostResponseDTO.setVoteCount(post.getVoteCount());
        newPostResponseDTO.setCommentCount(post.getCommentList().size());
        newPostResponseDTO.setDuration(ChronoUnit.HOURS.between(post.getCreatedDate(), LocalDateTime.now()));
        Vote foundVote = voteRepository.findByPostAndUser(post, userService.findLoggedInUser());
        //nu arunc exceptie deoarece ma folosesc de variabila foundVote doar pentru construirea raspunsului DTO
        if (foundVote != null) {
            if (foundVote.getVoteType().getValue() == -1) {
                newPostResponseDTO.setUpVoted(false);
                newPostResponseDTO.setDownVoted(true);
            } else {
                newPostResponseDTO.setUpVoted(true);
                newPostResponseDTO.setDownVoted(false);
            }
        } else {
            newPostResponseDTO.setUpVoted(false);
            newPostResponseDTO.setDownVoted(false);
        }
        return newPostResponseDTO;
    }

    public PostResponseDTO getPostBy(Long id) {
        Post foundPost = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "post not found"));
        return constructNewPostResponseDTO(foundPost);
    }

    public List<PostResponseDTO> getPostsBySubreddit(Long id) {
        //1. este redundant sa caut subreddit-ul dupa id (si sa arunc exceptie daca nu se gaseste in baza de date)
        //apoi sa caut post-urile dupa subreddit-ul gasit in baza de date??
        //2. ALTERNATIVA e sa caut post-urile dupa id-ul subreddit-ului, dar in situatia in care subreddit-ul nu se gaseste in BD
        //nu as avea posibilitatea de a arunca exceptie pt al anunta pe utilizator
        Subreddit foundSubreddit = subredditService.findSubreddit(id);
        List<Post> postListDB = postRepository.findBySubreddit(foundSubreddit);
        return transformPostListDBToPostResponseDTOList(postListDB);
    }

    public List<PostResponseDTO> getPostsByUser(Long id) {
        User foundUser = userService.findUser(id);
        List<Post> postListDB = postRepository.findByUser(foundUser);
        return transformPostListDBToPostResponseDTOList(postListDB);
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the post was not found"));
    }

    public Post update(Post post){
        return postRepository.save(post);
    }
}
