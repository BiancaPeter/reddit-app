package com.reddit.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference(value = "post-vote")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-vote")
    private User user;

    public Vote() {
    }

    public Vote(Long id, VoteType voteType, Post post, User user) {
        this.id = id;
        this.voteType = voteType;
        this.post = post;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
