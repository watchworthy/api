package com.watchworthy.api.service;

import com.watchworthy.api.entity.PasswordResetToken;
import org.springframework.stereotype.Service;

public interface PasswordResetTokenService {
    PasswordResetToken findByToken(String token);
    PasswordResetToken save(PasswordResetToken token);
}
