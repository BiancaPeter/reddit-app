package com.reddit.app;

import com.reddit.app.DTO.VoteRequestDTO;
import com.reddit.app.model.Post;
import com.reddit.app.model.User;
import com.reddit.app.model.Vote;
import com.reddit.app.model.VoteType;
import com.reddit.app.repository.VoteRepository;
import com.reddit.app.service.PostService;
import com.reddit.app.service.UserService;
import com.reddit.app.service.VoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class VoteServiceTests {
    @InjectMocks
    private VoteService voteService;

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Mock
    private VoteRepository voteRepository;

    @Test
    void testAddVote_ShouldReturnAddedVote() {
        //given
        Post foundPost = new Post(1L, "post1", "description1", 3, null, null, null, null, null);
        User foundUser = new User(1L, "username", "password", "email", null, null, null, null);
        Vote foundVote = null;
        Vote voteToBeAddded = new Vote(1L, VoteType.UP_VOTE, foundPost, foundUser);

        when(postService.findPost(any())).thenReturn(foundPost);
        when(userService.findLoggedInUser()).thenReturn(foundUser);
        when(voteRepository.findByPostAndUser(any(),any())).thenReturn(foundVote);
        when(voteRepository.save(any())).thenReturn(voteToBeAddded);


        VoteRequestDTO voteRequestDTO = new VoteRequestDTO(VoteType.UP_VOTE,foundPost.getId());
        Vote result = voteService.addVote(voteRequestDTO);
        assertEquals(VoteType.UP_VOTE, result.getVoteType());
        assertEquals(4, foundPost.getVoteCount());
    }
}
