package com.reddit.app.DTO;

import java.time.LocalDateTime;

public class CommentResponseDTO {
    private Long id;  //
    private Long postId;  //
    private LocalDateTime createdDate; //
    private String text;  //
    private String username;

    public CommentResponseDTO() {
    }

    public CommentResponseDTO(Long id, Long postId, LocalDateTime createdDate, String text, String username) {
        this.id = id;
        this.postId = postId;
        this.createdDate = createdDate;
        this.text = text;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
