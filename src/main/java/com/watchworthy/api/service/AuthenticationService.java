package com.watchworthy.api.service;

import com.watchworthy.api.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationService {
    ResponseEntity<String> authenticate(@Valid @RequestBody LoginDTO authRequest);
}
