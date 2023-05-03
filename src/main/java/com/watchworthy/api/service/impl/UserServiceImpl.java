package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.SignUpDTO;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.exception.PasswordNotMatchException;
import com.watchworthy.api.exception.UserAlreadyExistException;
import com.watchworthy.api.repository.UserRepository;
import com.watchworthy.api.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.watchworthy.api.exception.EmptyValueExistException;

import javax.swing.text.html.Option;
import java.util.Optional;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDTO signUpDTO) {
        String email = Optional.ofNullable(signUpDTO.getEmail()).orElseThrow(EmptyValueExistException::new);
        String firstName = Optional.ofNullable(signUpDTO.getFirstName()).orElseThrow(EmptyValueExistException::new);
        String lastName = Optional.ofNullable(signUpDTO.getLastName()).orElseThrow(EmptyValueExistException::new);
        String password = Optional.ofNullable(signUpDTO.getPassword()).orElseThrow(EmptyValueExistException::new);
        String confirmPassword = Optional.ofNullable(signUpDTO.getConfirmPassword()).orElseThrow(EmptyValueExistException::new);

        boolean alreadyExists = userRepository.existsByEmail(email);
        if(alreadyExists){
            throw new UserAlreadyExistException();
        }
        if(!password.equals(confirmPassword)){
            throw new PasswordNotMatchException();
        }
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(
          email,
          firstName,
          lastName,
                encodedPassword

        );
        logger.info("Created User" + newUser);
        userRepository.save(newUser);
    }
}
