package com.watchworthy.api.service.impl;

import com.watchworthy.api.repository.UserRepository;
import com.watchworthy.api.service.UserService;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository _userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this._userRepository=userRepository;
    }
}
