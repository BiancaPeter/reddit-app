package com.reddit.app.repository;

import com.reddit.app.model.Post;
import com.reddit.app.model.User;
import com.reddit.app.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    Vote findByPostAndUser(Post post, User user);
}
