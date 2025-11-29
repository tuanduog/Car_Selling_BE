package com.sec.car_selling.controller;

import com.sec.car_selling.dto.request.LoginRequest;
import com.sec.car_selling.dto.response.BaseResponse;
import com.sec.car_selling.dto.response.LoginResponse;
import com.sec.car_selling.entity.User;
import com.sec.car_selling.repository.UserRepository;
import com.sec.car_selling.service.JwtService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginController {

    AuthenticationManager authenticationManager;

    JwtService jwtService;

    UserRepository userRepository;

    public LoginController(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/v1")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String token = jwtService.generateToken(request.getEmail(), user.getRole());
        LoginResponse loginResponse = new
                LoginResponse(token, user.getFullName(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), loginResponse, "Đăng nhập thành công"));
    }
}
