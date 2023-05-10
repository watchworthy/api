package com.watchworthy.api.controller;

import com.watchworthy.api.constant.Controller;
import com.watchworthy.api.dto.BasicResponse;
import com.watchworthy.api.dto.ChangePasswordDTO;
import com.watchworthy.api.dto.SignUpDTO;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.exception.InvalidCredentialsException;
import com.watchworthy.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.watchworthy.api.dto.TokenContainingResponse;
import com.watchworthy.api.dto.LoginDTO;

@RestController
@RequestMapping("/user")
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


    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getUserProfile(@PathVariable String email) {
        User user = userService.getUserProfile(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
