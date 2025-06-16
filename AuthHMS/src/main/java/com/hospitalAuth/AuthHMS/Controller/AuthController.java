package com.hospitalAuth.AuthHMS.Controller;

import com.hospitalAuth.AuthHMS.Dto.AuthResponse;
import com.hospitalAuth.AuthHMS.Dto.LoginRequest;
import com.hospitalAuth.AuthHMS.Dto.RegisterRequest;
import com.hospitalAuth.AuthHMS.Service.AuthService;
import com.hospitalAuth.AuthHMS.Utill.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    @Value("${google.client-id}")
    private String clientId;

    private final JwtUtil jwtUtil;
    private final AuthService asvc;

    public AuthController(AuthService asvc, JwtUtil jwtUtil) {
        this.asvc = asvc;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register") public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest r) {
        asvc.register(r);
        return ResponseEntity.ok("Registered successfully");
    }
    @PostMapping("/login") public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest r) {
        return ResponseEntity.ok(asvc.login(r));
    }


}
