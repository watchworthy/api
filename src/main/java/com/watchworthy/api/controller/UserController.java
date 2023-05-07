package com.watchworthy.api.controller;

import com.watchworthy.api.constant.Controller;
import com.watchworthy.api.dto.BasicResponse;
import com.watchworthy.api.dto.ChangePasswordDTO;
import com.watchworthy.api.dto.SignUpDTO;
import com.watchworthy.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
