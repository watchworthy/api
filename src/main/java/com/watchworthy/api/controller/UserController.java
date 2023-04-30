package com.watchworthy.api.controller;

import com.watchworthy.api.constant.Controller;
import com.watchworthy.api.dto.BasicResponse;
import com.watchworthy.api.dto.SignUpDTO;
import com.watchworthy.api.dto.UserDTO;
import com.watchworthy.api.service.UserService;
import okhttp3.OkHttp;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService _userService;

    public UserController(UserService userService) {
        _userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<BasicResponse> signUp(@RequestBody SignUpDTO signUpDTO) {
        _userService.signUp(signUpDTO);

        BasicResponse response = new BasicResponse(HttpStatus.OK, Controller.SIGN_UP_SUCCESS_MESSAGE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
