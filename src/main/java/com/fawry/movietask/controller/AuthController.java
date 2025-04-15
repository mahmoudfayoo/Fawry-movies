package com.fawry.movietask.controller;

import com.fawry.movietask.dto.LoginRequestDto;
import com.fawry.movietask.dto.LoginResponseDto;
import com.fawry.movietask.dto.UserRegisterRequestDto;
import com.fawry.movietask.integration.OmdbClient;
import com.fawry.movietask.repository.UserRepository;
import com.fawry.movietask.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OmdbClient omdbClient ;
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequestDto registerRequestDto) {
        return authService.registerUser(registerRequestDto);
    }
    @GetMapping("/get-users")
    public Object getUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/add-movie")
    public Object getMovie(@RequestParam("i") String movieId , @RequestParam("apikey") String apikey) {
        return omdbClient.getPostById(movieId,apikey);
    }
}
