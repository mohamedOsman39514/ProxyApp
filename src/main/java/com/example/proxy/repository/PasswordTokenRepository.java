package com.example.proxy.repository;

import com.example.proxy.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query(value = "SELECT u FROM PasswordResetToken u WHERE u.token= :token")
    PasswordResetToken findByToken(String token);

}
