package com.samah.userservice.repository;

import com.samah.userservice.entity.ResetVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetVerificationTokenRepository extends JpaRepository<ResetVerificationToken, Long> {
    ResetVerificationToken findByToken(String token);

    ResetVerificationToken findByUserId(Long id);
}
