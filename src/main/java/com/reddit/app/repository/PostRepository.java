package com.reddit.app.repository;

import com.reddit.app.model.Post;
import com.reddit.app.model.Subreddit;
import com.reddit.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
}
