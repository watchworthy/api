package com.watchworthy.api.service.impl;

import com.watchworthy.api.entity.PasswordResetToken;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.repository.PasswordResetTokenRepository;
import com.watchworthy.api.service.PasswordResetTokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetTokenServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token).orElse(null);
    }

    @Override
    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        User user = passwordResetToken.getUser();
        Optional<PasswordResetToken> existingToken = passwordResetTokenRepository.findByUser(user);
        existingToken.ifPresent(passwordResetTokenRepository::delete);
        return passwordResetTokenRepository.save(passwordResetToken);
    }
}
