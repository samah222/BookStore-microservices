package com.samah.userservice.repository;

import com.samah.userservice.entity.ChangePasswordVerificationToken;
import com.samah.userservice.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangePasswordVerificationTokenRepository extends JpaRepository<ChangePasswordVerificationToken, Long> {
    ChangePasswordVerificationToken findByToken(String token);

    ChangePasswordVerificationToken findByUserId(Long id);
}
