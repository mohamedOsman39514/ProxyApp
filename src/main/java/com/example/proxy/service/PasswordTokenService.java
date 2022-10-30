package com.example.proxy.service;

import com.example.proxy.model.PasswordResetToken;
import com.example.proxy.model.User;
import com.example.proxy.repository.PasswordTokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class PasswordTokenService {

//    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Value("${jwt.resetTokenExpiration}")
    private int resetTokenExpiration;

    public void createPasswordResetTokenForUser(User user, String token) {

        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setUser(user);
        myToken.setToken(token);
        myToken.setExpiryDate(new Date(System.currentTimeMillis() + resetTokenExpiration));
        passwordTokenRepository.save(myToken);
    }

    public PasswordResetToken getResetToken(String Token) {
        return passwordTokenRepository.findByToken(Token);
    }
}
