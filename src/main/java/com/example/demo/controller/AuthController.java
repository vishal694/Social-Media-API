package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.JwtAuthRequest;
import com.example.demo.blog.security.JwtAuthResponse;
import com.example.demo.blog.security.JwtTokenHelper;
import com.example.demo.config.WelcomeMail;
import com.example.demo.exception.ApiException;
import com.example.demo.payloads.UserDto;
import com.example.demo.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;

import io.swagger.annotations.ApiResponses;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/auth")
@Api(tags = "Login", value = "UserLogin", description = "Users can Login and Register")
public class AuthController {

	Logger log = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService UserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private WelcomeMail welcomeMail;

	@PostMapping("/login")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = AuthController.class, responseContainer = "List") })
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {
		log.debug("Request for Login", jwtAuthRequest.getUserName());
		try {
			this.authenticate(jwtAuthRequest.getUserName(), jwtAuthRequest.getPassword());

		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		UserDetails userDetails = this.UserDetailsService.loadUserByUsername(jwtAuthRequest.getUserName());

		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
	}

	private void authenticate(String userName, String password) {
		log.debug("Request of authenticate", userName);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userName, password);
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			throw new ApiException("Invalid userName or Password! ");
		}
	}

	@PostMapping("/register")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = AuthController.class, responseContainer = "List") })
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) throws AddressException, MessagingException, IOException {

		log.debug("Request for register", userDto.getId());
		welcomeMail.sendMail(userDto.getEmail());
		UserDto registeredUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);

	}
}
