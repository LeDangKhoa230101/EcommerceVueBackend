package com.example.vuejsbackend.VueBackend.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vuejsbackend.VueBackend.dto.ResponseDto;
import com.example.vuejsbackend.VueBackend.dto.user.LoginDto;
import com.example.vuejsbackend.VueBackend.dto.user.SignInResponseDto;
import com.example.vuejsbackend.VueBackend.dto.user.SignupDto;
import com.example.vuejsbackend.VueBackend.exceptions.AuthenticationFailException;
import com.example.vuejsbackend.VueBackend.exceptions.CustomException;
import com.example.vuejsbackend.VueBackend.model.AuthenticationToken;
import com.example.vuejsbackend.VueBackend.model.User;
import com.example.vuejsbackend.VueBackend.repository.TokenRepository;
import com.example.vuejsbackend.VueBackend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository reposUser;

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	TokenRepository tokenRepository;

	@Transactional
	public ResponseDto signUp(SignupDto signupDto) {
		// check user is already present
		if (Objects.nonNull(reposUser.findByEmail(signupDto.getEmail()))) {
			throw new CustomException("user already present");
		}

		// hash password
		String encryptedpassword = signupDto.getPassword();
		try {
			encryptedpassword = hashPassword(signupDto.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// save user
		User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(),
				encryptedpassword, "ROLE_USER");
		reposUser.save(user);

		// create token
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		authenticationService.saveConfirmationToken(authenticationToken);

		ResponseDto responseDto = new ResponseDto("success", "user created successful!");
		return responseDto;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	public SignInResponseDto login(LoginDto loginDto) {
		// find user by email
		User user = reposUser.findByEmail(loginDto.getEmail());

		if (Objects.isNull(user)) {
			throw new AuthenticationFailException("user is not valid");
		}

		// hash password
		try {
			if (!user.getPassword().equals(hashPassword(loginDto.getPassword()))) {
				throw new AuthenticationFailException("wrong password");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// if pass match
		AuthenticationToken token = authenticationService.getToken(user);

		if(token == null) {
			// create token
			final AuthenticationToken authenticationToken = new AuthenticationToken(user);
			authenticationService.saveConfirmationToken(authenticationToken);
		}

		return new SignInResponseDto("success", token.getToken(), user.getRole());
	}

	@Transactional
	public ResponseDto logout(String token) {
		AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
		if (authenticationToken != null) {
			tokenRepository.delete(authenticationToken);
			return new ResponseDto("success", "Logout successful!");
		} 
		return new ResponseDto("failed", "Logout failed!");
	}

}
