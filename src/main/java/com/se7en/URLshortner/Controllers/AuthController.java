package com.se7en.URLshortner.Controllers;

import com.se7en.URLshortner.Payloads.AuthenticationResponse;
import com.se7en.URLshortner.config.AuthenticationRequest;
import com.se7en.URLshortner.config.AuthenticationService;
import com.se7en.URLshortner.config.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> createUser(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.createUser(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> loginUser(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticateUser(request));
    }

}
