package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.ChangePasswordDTO;
import com.watchworthy.api.dto.LoginDTO;
import com.watchworthy.api.dto.SignUpDTO;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.exception.*;
import com.watchworthy.api.repository.UserRepository;
import com.watchworthy.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import com.watchworthy.api.exception.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.watchworthy.api.util.JwtUtil;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private AuthenticationManager _authenticationManager;
    private JwtUtil _jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this._userRepository=userRepository;
        this._passwordEncoder = passwordEncoder;
        this._authenticationManager = authenticationManager;
        this._jwtUtil = jwtUtil;
    }


    @Override
    public void signUp(SignUpDTO signUpDTO) {
        String email = Optional.ofNullable(signUpDTO.getEmail()).orElseThrow(EmptyValueExistException::new);
        String firstName = Optional.ofNullable(signUpDTO.getFirstName()).orElseThrow(EmptyValueExistException::new);
        String lastName = Optional.ofNullable(signUpDTO.getLastName()).orElseThrow(EmptyValueExistException::new);
        String password = Optional.ofNullable(signUpDTO.getPassword()).orElseThrow(EmptyValueExistException::new);
        String confirmPassword = Optional.ofNullable(signUpDTO.getConfirmPassword()).orElseThrow(EmptyValueExistException::new);

        boolean alreadyExists = _userRepository.existsByEmail(email);
        if(alreadyExists){
            throw new UserAlreadyExistException();
        }
        if(!password.equals(confirmPassword)){
            throw new PasswordNotMatchException();
        }
        String encodedPassword = _passwordEncoder.encode(password);
        User newUser = new User(
          email,
          firstName,
          lastName,
                encodedPassword

        );
        logger.info("Created User" + newUser);
        _userRepository.save(newUser);
    }

    public String login(LoginDTO loginDTO) throws Exception {
        String email = Optional.ofNullable(loginDTO.getEmail()).orElseThrow(EmptyValueExistException::new);
        String password = Optional.ofNullable(loginDTO.getPassword()).orElseThrow(EmptyValueExistException::new);

        Optional<User> user = _userRepository.findByEmail(email);

        if(!user.isPresent()){
            throw new UserNotExistException();
        }

        try{
            _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    ));
        }catch(AuthenticationException e){
            throw new InvalidCredentialsException("Invalid credentials provided.");
        }

        return _jwtUtil.generateToken(email);
    }

    @Override
    public boolean changePassword(Long userId, ChangePasswordDTO changePasswordDTO) {
        User user = _userRepository.findById(userId).orElse(null);
        if(user == null){
            return false;
        }

        if (_passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())) {
            if(changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())){
                String encodedNewPassword = _passwordEncoder.encode(changePasswordDTO.getNewPassword());
                user.setPassword(encodedNewPassword);
                _userRepository.save(user);
                return true;
            }
        }
        return false;
    }

}
