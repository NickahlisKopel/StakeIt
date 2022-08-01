package com.careerdevs.stakeit.Repositories;

import com.careerdevs.stakeit.models.Comment;
import com.careerdevs.stakeit.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProfile_id(Long user_id);
    List<Comment> findAllByProfile_name(String user_name);
}
