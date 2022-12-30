package com.reddit.app.DTO;

import com.reddit.app.model.VoteType;

public class VoteRequestDTO {
    private VoteType voteType;
    private Long postId;

    public VoteRequestDTO(VoteType voteType, Long postId) {
        this.voteType = voteType;
        this.postId = postId;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
