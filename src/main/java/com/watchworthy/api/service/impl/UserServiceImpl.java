package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.ChangePasswordDTO;
import com.watchworthy.api.dto.LoginDTO;
import com.watchworthy.api.dto.SignUpDTO;
import com.watchworthy.api.entity.PasswordResetToken;
import com.watchworthy.api.entity.Role;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.exception.*;
import com.watchworthy.api.model.EmailType;
import com.watchworthy.api.repository.RoleRepository;
import com.watchworthy.api.repository.UserRepository;
import com.watchworthy.api.service.EmailService;
import com.watchworthy.api.service.PasswordResetTokenService;
import com.watchworthy.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final EmailService emailService;
    private final RoleRepository roleRepository;
    private final PasswordResetTokenService passwordResetTokenService;
    private AuthenticationManager _authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, RoleRepository roleRepository, PasswordResetTokenService passwordResetTokenService, AuthenticationManager authenticationManager){
        this._userRepository=userRepository;
        this._passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
        this.passwordResetTokenService = passwordResetTokenService;
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
            emailService.sendEmail(newUser.getEmail(), newUser.getFirstName(), EmailType.WELCOME,null);
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

    @Override
    public void forgotPassword(String email) {
        PasswordResetToken token = new PasswordResetToken();
        User user = _userRepository.findByEmail(email).orElse(null);
        if(user == null){
            throw new RuntimeException("user does not exist");
        }
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpirationDate(LocalDateTime.now().plusMinutes(30));
        token = passwordResetTokenService.save(token);
        if(token == null){
            throw new RuntimeException("token error");
        }
        logger.info("https://localhost:3000/reset-password?token=" + token.getToken());
        try {
            emailService.sendEmail(user.getEmail(), user.getFirstName(), EmailType.RESET_PASSWORD,token.getToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void resetPassword(String token,String password) {
    PasswordResetToken passwordResetToken = passwordResetTokenService.findByToken(token);
    if(passwordResetToken == null){
        throw new RuntimeException("TOKEN_NOT_FOUND");
    }else if(passwordResetToken.getExpirationDate().isBefore(LocalDateTime.now())){
        throw new RuntimeException("TOKEN_EXPIRED");
    }

    User user = passwordResetToken.getUser();
    String encodedPassword = _passwordEncoder.encode(password);
    user.setPassword(encodedPassword);
    _userRepository.save(user);
    }

//


}
