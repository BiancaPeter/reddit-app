package com.reddit.app.DTO;

public class PostRequestDTO {
    private String postName;
    private String description;
    private Long subredditId;

    public PostRequestDTO(String postName, String description, Long subredditId) {
        this.postName = postName;
        this.description = description;
        this.subredditId = subredditId;
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

    public Long getSubredditId() {
        return subredditId;
    }

    public void setSubredditId(Long subredditId) {
        this.subredditId = subredditId;
    }
}
