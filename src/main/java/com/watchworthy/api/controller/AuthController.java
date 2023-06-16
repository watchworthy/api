package com.watchworthy.api.controller;

import com.watchworthy.api.dto.LoginDTO;
import com.watchworthy.api.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

 private final AuthenticationService authenticationService;

    @PostMapping("")
    public ResponseEntity<String> authenticate(@Valid @RequestBody LoginDTO authRequest) {
        return authenticationService.authenticate(authRequest);
    }

}
