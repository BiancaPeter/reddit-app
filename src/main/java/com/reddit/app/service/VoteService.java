package com.reddit.app.service;

import com.reddit.app.DTO.VoteRequestDTO;
import com.reddit.app.model.Post;
import com.reddit.app.model.User;
import com.reddit.app.model.Vote;
import com.reddit.app.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VoteService {
    private VoteRepository voteRepository;

    private PostService postService;

    private UserService userService;

    @Autowired
    public VoteService(VoteRepository voteRepository, PostService postService, UserService userService) {
        this.voteRepository = voteRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public Vote addVote(VoteRequestDTO voteRequestDTO) {
        Post foundPost = postService.findPost(voteRequestDTO.getPostId());
        User foundUser = userService.findLoggedInUser();
        Vote foundVote = voteRepository.findByPostAndUser(foundPost, foundUser);
        if (foundVote == null) {
            Vote vote = new Vote();
            vote.setVoteType(voteRequestDTO.getVoteType());
            vote.setPost(foundPost);
            vote.setUser(foundUser);
            foundPost.setVoteCount(foundPost.getVoteCount() + vote.getVoteType().getValue());
            postService.update(foundPost);
            return voteRepository.save(vote);
        } else if (foundVote.getVoteType().equals(voteRequestDTO.getVoteType())) {
            voteRepository.delete(foundVote);
            foundPost.setVoteCount(foundPost.getVoteCount() - voteRequestDTO.getVoteType().getValue());
            postService.update(foundPost);
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "there was already a " + voteRequestDTO.getVoteType() + ", so the vote was deleted");
        } else {
            foundVote.setVoteType(voteRequestDTO.getVoteType());
            foundPost.setVoteCount(foundPost.getVoteCount() + 2 * voteRequestDTO.getVoteType().getValue());
            postService.update(foundPost);
            return voteRepository.save(foundVote);
        }
    }

}
