package com.watchworthy.api.service;

import com.watchworthy.api.dto.ChangePasswordDTO;
import com.watchworthy.api.dto.LoginDTO;
import com.watchworthy.api.dto.SignUpDTO;
import com.watchworthy.api.entity.User;

import java.util.Optional;

public interface UserService {
    void signUp (SignUpDTO signUpDTO);
    String login(LoginDTO loginDTO) throws Exception;
    boolean changePassword(Long userId, ChangePasswordDTO changePasswordDTO);
    Optional<User> findEntityByUsername(String username);
    boolean changeUserStatus(Long userId, boolean isActive );
    User getUserProfile(String email);

}
