package com.watchworthy.api.controller;

import com.watchworthy.api.constant.Controller;
import com.watchworthy.api.dto.*;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.exception.InvalidCredentialsException;
import com.watchworthy.api.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<BasicResponse> signUp(@RequestBody SignUpDTO signUpDTO) {
        userService.signUp(signUpDTO);

        BasicResponse response = new BasicResponse(HttpStatus.OK, Controller.SIGN_UP_SUCCESS_MESSAGE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenContainingResponse> login(@RequestBody LoginDTO loginDTO) {
        try {
            String token = userService.login(loginDTO);
            TokenContainingResponse response = new TokenContainingResponse(HttpStatus.OK, Controller.LOG_IN_SUCCESS_MESSAGE, token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            TokenContainingResponse response = new TokenContainingResponse(HttpStatus.UNAUTHORIZED, "Invalid email or password", null);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            TokenContainingResponse response = new TokenContainingResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while logging in", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/changepassword/{userId}")
    public ResponseEntity<Void> changePassword(@PathVariable Long userId, @RequestBody ChangePasswordDTO changePasswordDTO) {
        boolean result = userService.changePassword(userId,changePasswordDTO);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/changeuserstatus/{userId}/{isActive}")
    public ResponseEntity<Void> changeUserStatus(@PathVariable Long userId, @PathVariable boolean isActive ) {
        boolean result = userService.changeUserStatus(userId,isActive);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Cacheable(value = "userProfile")
    @GetMapping("/profile/{username}")
    public UserProfileDTO getUserProfile(@PathVariable String username) {
        User user = userService.getUserProfile(username);
        if (user != null) {
            return mapUserToDTO(user);
        } else {
            return null; // Or handle the case when the user is not found
        }
    }


    private UserProfileDTO mapUserToDTO(User user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserId(user.getId());
        userProfileDTO.setEmail(user.getEmail());
        userProfileDTO.setFirstName(user.getFirstName());
        userProfileDTO.setLastName(user.getLastName());
        return userProfileDTO;
    }

}
