package com.hospitalAuth.AuthHMS.Service.Impl;

import com.hospitalAuth.AuthHMS.Dto.AuthResponse;
import com.hospitalAuth.AuthHMS.Dto.LoginRequest;
import com.hospitalAuth.AuthHMS.Dto.RegisterRequest;
import com.hospitalAuth.AuthHMS.Entity.Role;
import com.hospitalAuth.AuthHMS.Entity.User;
import com.hospitalAuth.AuthHMS.Repository.RoleRepository;
import com.hospitalAuth.AuthHMS.Repository.UserRepository;
import com.hospitalAuth.AuthHMS.Service.AuthService;
import com.hospitalAuth.AuthHMS.Utill.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(RoleRepository roleRepository,
                           UserRepository userRepository,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtService,
                           PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void register(RegisterRequest req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.getRoles().add(defaultRole);

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(), req.getPassword()
                )
        );

        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User found: " + user.getUsername());
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
