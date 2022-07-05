package com.careerdevs.stakeit.Repositories;

import com.careerdevs.stakeit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);
}
