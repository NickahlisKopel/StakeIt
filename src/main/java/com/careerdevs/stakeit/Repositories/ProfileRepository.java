package com.careerdevs.stakeit.Repositories;

import com.careerdevs.stakeit.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByName(String username);
}
