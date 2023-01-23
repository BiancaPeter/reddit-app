package com.reddit.app.mapper;

import com.reddit.app.DTO.CommentRequestDTO;
import com.reddit.app.DTO.CommentResponseDTO;
import com.reddit.app.DTO.PostRequestDTO;
import com.reddit.app.DTO.PostResponseDTO;
import com.reddit.app.model.Comment;
import com.reddit.app.model.Post;
import com.reddit.app.repository.PostRepository;
import com.reddit.app.service.CommentService;
import com.reddit.app.service.PostService;
import com.reddit.app.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Autowired
    protected UserService userService;

    @Autowired
    protected PostService postService;

    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "user", expression = "java(userService.findLoggedInUser())")
    @Mapping(target = "post", expression = "java(postService.findPost(commentRequestDTO.getPostId()))")
    public abstract Comment mapDtoToComment(CommentRequestDTO commentRequestDTO);

    @Mapping(target = "postId", expression = "java(comment.getPost().getId())")
    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    public abstract CommentResponseDTO mapCommentToDto(Comment comment);

}
