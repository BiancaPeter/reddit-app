package com.reddit.app.mapper;

import com.reddit.app.DTO.PostRequestDTO;
import com.reddit.app.DTO.PostResponseDTO;
import com.reddit.app.model.Post;
import com.reddit.app.model.Vote;
import com.reddit.app.service.PostService;
import com.reddit.app.service.SubredditService;
import com.reddit.app.service.UserService;
import com.reddit.app.service.VoteService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    protected SubredditService subredditService;

    @Autowired
    protected VoteService voteService;

    @Autowired
    protected UserService userService;

    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "subreddit", expression = "java(subredditService.findSubreddit(postRequestDTO.getSubredditId()))")
    @Mapping(target = "user", expression = "java(userService.findLoggedInUser())")
    public abstract Post mapDtoToPost(PostRequestDTO postRequestDTO);

    @Mapping(target = "userName", expression = "java(post.getUser().getUsername())")
    @Mapping(target = "subredditName", expression = "java(post.getSubreddit().getName())")
    @Mapping(target = "commentCount", expression = "java(post.getCommentList().size())")
    @Mapping(target = "duration", expression = "java(java.time.temporal.ChronoUnit.HOURS.between(post.getCreatedDate(), java.time.LocalDateTime.now()))")
    @Mapping(target = "upVoted", expression = "java(voteService.setUpVote(post))")
    @Mapping(target = "downVoted", expression = "java(voteService.setDownVote(post))")
    public abstract PostResponseDTO mapPostToDto(Post post);
}


