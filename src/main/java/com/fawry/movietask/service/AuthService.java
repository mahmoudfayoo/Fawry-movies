package com.fawry.movietask.service;

import com.fawry.movietask.dto.LoginRequestDto;
import com.fawry.movietask.dto.LoginResponseDto;
import com.fawry.movietask.dto.UserRegisterRequestDto;
import com.fawry.movietask.entity.User;
import com.fawry.movietask.exceptions.DoublicateUserException;
import com.fawry.movietask.exceptions.InvalidUserNameOrPassException;
import com.fawry.movietask.repository.UserRepository;
import com.fawry.movietask.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
 import java.util.Optional;

@Service
public class AuthService {

    UserRepository userRepository;
    JwtService jwtService;
    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    public String registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        Optional<User> userFromDB =  userRepository.findByUsername(userRegisterRequestDto.getUsername());
        if (userFromDB.isPresent()) {
            throw new DoublicateUserException("Username is already in use");
        }
        User clientUser = createUserEntity(userRegisterRequestDto);
        userRepository.saveAndFlush(clientUser);
        return  "User " + userRegisterRequestDto.getUsername() + " registered successfully";
    }
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        checkUserValidity(loginRequestDto);
        Optional<User> user = userRepository.findByUsername(loginRequestDto.getUsername());
        if (user.isPresent()) {
           if (user.get().getPassword().equals(loginRequestDto.getPassword()))
           {
               LoginResponseDto loginResponseDto = new LoginResponseDto();
               loginResponseDto.setToken(generateToken(loginRequestDto));
               return loginResponseDto;
           }else
           {
               throw  new InvalidUserNameOrPassException("Invalid username or password");
           }
        }
        throw  new InvalidUserNameOrPassException("Invalid username or password");
    }
    private void checkUserValidity(LoginRequestDto loginRequestDto) {
        if(loginRequestDto.getUsername() == null || loginRequestDto.getPassword() == null) {
            throw new RuntimeException("Invalid login request");
        }
    }
    private String generateToken(LoginRequestDto loginRequestDto) {
        Map<String, Object> claims = getClaims(loginRequestDto);
        return jwtService.generateToken(loginRequestDto.getUsername(),claims);
    }
    private User createUserEntity(UserRegisterRequestDto userRegisterRequestDto) {
        User user = new User();
        user.setUsername(userRegisterRequestDto.getUsername());
        user.setPassword(userRegisterRequestDto.getPassword());
        user.setGender(userRegisterRequestDto.getEmail());
        return user;
    }

    private Map<String,Object> getClaims(LoginRequestDto loginRequestDto) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("username",loginRequestDto.getUsername());
        claims.put("password",loginRequestDto.getPassword());
        if(loginRequestDto.getUsername().equals("admin")&&loginRequestDto.getPassword().equals("123")){
            claims.put("userType","admin");
        }else{
            claims.put("userType","user");
        }
        return claims;
    }

}