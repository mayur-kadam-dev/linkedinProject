package com.codingshuttle.linkedin.userService.controller;

import com.codingshuttle.linkedin.userService.dto.LoginRequestDto;
import com.codingshuttle.linkedin.userService.dto.SignUpRequestDto;
import com.codingshuttle.linkedin.userService.dto.UserDto;
import com.codingshuttle.linkedin.userService.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class UserController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signupRequestDto) {
        UserDto userDto = authService.signUp(signupRequestDto);
        log.info("User signed up successfully: {}", userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }
}
