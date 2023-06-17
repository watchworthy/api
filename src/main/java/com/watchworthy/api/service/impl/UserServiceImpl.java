package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.ChangePasswordDTO;
import com.watchworthy.api.dto.LoginDTO;
import com.watchworthy.api.dto.SignUpDTO;
import com.watchworthy.api.entity.Role;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.exception.*;
import com.watchworthy.api.model.EmailType;
import com.watchworthy.api.repository.RoleRepository;
import com.watchworthy.api.repository.UserRepository;
import com.watchworthy.api.service.EmailService;
import com.watchworthy.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final EmailService emailService;
    private final RoleRepository roleRepository;
    private AuthenticationManager _authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, RoleRepository roleRepository, AuthenticationManager authenticationManager){
        this._userRepository=userRepository;
        this._passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
        this._authenticationManager = authenticationManager;
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<User> findEntityByUsername(String username) {
        return _userRepository.findByUsername(username);
    }


    @Override
    public void signUp(SignUpDTO signUpDTO) {
        String email = Optional.ofNullable(signUpDTO.getEmail()).orElseThrow(EmptyValueExistException::new);
        String username = Optional.ofNullable(signUpDTO.getUsername()).orElseThrow(EmptyValueExistException::new);
        String firstName = Optional.ofNullable(signUpDTO.getFirstName()).orElseThrow(EmptyValueExistException::new);
        String lastName = Optional.ofNullable(signUpDTO.getLastName()).orElseThrow(EmptyValueExistException::new);
        String password = Optional.ofNullable(signUpDTO.getPassword()).orElseThrow(EmptyValueExistException::new);
        String confirmPassword = Optional.ofNullable(signUpDTO.getConfirmPassword()).orElseThrow(EmptyValueExistException::new);
        List<String> roles = signUpDTO.getRoles();
        if(roles == null){
            roles = new ArrayList<>();
            roles.add("USER");
        }
        Set<Role> roleSet = roles.stream()
                .map(roleName -> {
                    Role role = roleRepository.findRoleByName(roleName).orElse(null);
                    if (role == null) {
                        role = new Role(roleName);
                        roleRepository.save(role);
                    }
                    return role;
                })
                .collect(Collectors.toSet());

        boolean alreadyExists = _userRepository.existsByEmail(email);
        if(alreadyExists){
            throw new UserAlreadyExistException();
        }
        if(!password.equals(confirmPassword)){
            throw new PasswordNotMatchException();
        }
        String encodedPassword = _passwordEncoder.encode(password);
        User newUser = new User(email,firstName,lastName,encodedPassword, true,roleSet,username);
        _userRepository.save(newUser);
        try {
            emailService.sendEmail(newUser.getEmail(), newUser.getFirstName(), EmailType.WELCOME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String login(LoginDTO loginDTO) throws Exception {
//        String email = Optional.ofNullable(loginDTO.getEmail()).orElseThrow(EmptyValueExistException::new);
//        String password = Optional.ofNullable(loginDTO.getPassword()).orElseThrow(EmptyValueExistException::new);
//
//        Optional<User> user = _userRepository.findByEmail(email);
//
//        if(!user.isPresent()){
//            throw new UserNotExistException();
//        }
//
//        try{
//            _authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            email,
//                            password
//                    ));
//        }catch(AuthenticationException e){
//            throw new InvalidCredentialsException("Invalid credentials provided.");
//        }
//
//        return _jwtUtil.generateToken(user.get().getId(),email,user.get().getAuthorities());
        return null;
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

    @Override
    public boolean changeUserStatus(Long userId, boolean isActive) {
        User user = _userRepository.findById(userId).orElse(null);
        if(user == null) {
            return false;
        }
        user.setActive(isActive);
        _userRepository.save(user);
        return true;
    }

    @Override
    public User getUserProfile(String username) {
        Optional<User> userOptional = _userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }

    @Override
    public void addPreferredGenres(Long userId, List<String> preferredGenres) {
        User user = _userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        user.getPreferredGenres().addAll(preferredGenres);

        _userRepository.save(user);
    }

    @Override
    public List<String> getPreferredGenres(Long userId) {
        User user = _userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        return user.getPreferredGenres();
    }



}
