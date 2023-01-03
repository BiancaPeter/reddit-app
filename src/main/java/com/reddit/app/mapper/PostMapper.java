package com.reddit.app.mapper;

import com.reddit.app.DTO.PostRequestDTO;
import com.reddit.app.model.Post;
import com.reddit.app.service.SubredditService;
import com.reddit.app.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    protected SubredditService subredditService;

    @Autowired
    protected UserService userService;

    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "subreddit", expression = "java(subredditService.findSubreddit(postRequestDTO.getSubredditId()))")
    @Mapping(target = "user", expression = "java(userService.findLoggedInUser())")
    public abstract Post mapDtoToPost(PostRequestDTO postRequestDTO);


}
