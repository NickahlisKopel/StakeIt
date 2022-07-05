package com.careerdevs.stakeit.Repositories;

import com.careerdevs.stakeit.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser_id(Long user_id);
    List<Post> findAllByUser_name(String user_name);
}
