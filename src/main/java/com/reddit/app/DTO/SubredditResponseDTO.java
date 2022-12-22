package com.reddit.app.DTO;

import java.time.LocalDateTime;

public class SubredditResponseDTO {

    private String name;
    private String description;
    private LocalDateTime createdDate;
    private Integer numberOfPosts;

    public SubredditResponseDTO(String name, String description, LocalDateTime createdDate, Integer numberOfPosts) {
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.numberOfPosts = numberOfPosts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(Integer numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }
}
