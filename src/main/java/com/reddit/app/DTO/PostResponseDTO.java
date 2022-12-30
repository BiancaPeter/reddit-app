package com.reddit.app.DTO;

import java.time.LocalDateTime;

public class PostResponseDTO {
    private Long id;
    private String postName;
    private String description;
    private String userName;
    private String subredditName;
    private int voteCount;
    private int commentCount;
    private Long duration;
    private boolean isUpVoted;
    private boolean isDownVoted;

    public PostResponseDTO() {
    }

    public PostResponseDTO(Long id, String postName, String description, String userName, String subredditName, int voteCount, int commentCount, Long duration, boolean isUpVoted, boolean isDownVoted) {
        this.id = id;
        this.postName = postName;
        this.description = description;
        this.userName = userName;
        this.subredditName = subredditName;
        this.voteCount = voteCount;
        this.commentCount = commentCount;
        this.duration = duration;
        this.isUpVoted = isUpVoted;
        this.isDownVoted = isDownVoted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubredditName() {
        return subredditName;
    }

    public void setSubredditName(String subredditName) {
        this.subredditName = subredditName;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public boolean isUpVoted() {
        return isUpVoted;
    }

    public void setUpVoted(boolean upVoted) {
        isUpVoted = upVoted;
    }

    public boolean isDownVoted() {
        return isDownVoted;
    }

    public void setDownVoted(boolean downVoted) {
        isDownVoted = downVoted;
    }
}
