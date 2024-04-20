package com.example.vuejsbackend.VueBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vuejsbackend.VueBackend.dto.ResponseDto;
import com.example.vuejsbackend.VueBackend.dto.user.LoginDto;
import com.example.vuejsbackend.VueBackend.dto.user.SignInResponseDto;
import com.example.vuejsbackend.VueBackend.dto.user.SignupDto;
import com.example.vuejsbackend.VueBackend.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	// signup
	@PostMapping("/signup")
	public ResponseDto registerUser(@RequestBody SignupDto signupDto) {
		return userService.signUp(signupDto);
	}
	
	@PostMapping("/login")
    public SignInResponseDto loginUser(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/logout")
    public ResponseDto logoutUser(@RequestParam("token") String token) {
    	return userService.logout(token);
    }

}
