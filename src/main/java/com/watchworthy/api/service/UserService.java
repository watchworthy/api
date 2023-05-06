package com.watchworthy.api.service;

import com.watchworthy.api.dto.ChangePasswordDTO;
import com.watchworthy.api.dto.LoginDTO;
import com.watchworthy.api.dto.SignUpDTO;
import org.springframework.stereotype.Service;

public interface UserService {
    void signUp (SignUpDTO signUpDTO);
    String login(LoginDTO loginDTO) throws Exception;
    boolean changePassword(Long userId, ChangePasswordDTO changePasswordDTO);


}
