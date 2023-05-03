package com.watchworthy.api.service;

import com.watchworthy.api.dto.SignUpDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void signUp (SignUpDTO signUpDTO);



}
