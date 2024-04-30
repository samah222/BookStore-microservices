package com.samah.userservice.entity;

import com.samah.userservice.utils.TokenExpirationTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_RESET_USER_VERIFY_TOKEN"))
    private User user;

    public ResetVerificationToken(User user, String token) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = TokenExpirationTime.calculateExpirationDate();
    }

    public ResetVerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = TokenExpirationTime.calculateExpirationDate();
    }
}
