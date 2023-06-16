package com.watchworthy.api.controller;

import com.watchworthy.api.dto.LoginDTO;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.service.UserService;
import com.watchworthy.api.util.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("")
    public ResponseEntity<String> authenticate(@Valid @RequestBody LoginDTO authRequest) {
        log.debug("REST request to authenticate : {}", authRequest.getUsername());
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);
        User user = userService.findEntityByUsername(authRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        final String jwt = jwtUtils.generateToken(user);
        return ResponseEntity.ok().body(jwt);
    }

}
