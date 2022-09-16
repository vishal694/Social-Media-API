package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.UserDto;
import com.example.demo.service.IUserService;

import io.swagger.annotations.Api;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
@Api(tags = "Profile", value = "UserProfile", description = "Users Profiles Operations")
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@PutMapping("/update-Users/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer userId,HttpServletRequest request) {
		log.debug("Request for update profile");
		UserDto updateUserDto = this.userService.updateuser(userDto, userId, (String) request.getSession().getAttribute("Email"));
		return ResponseEntity.ok(updateUserDto);

	}

	@DeleteMapping("/delete-Users/{userId}")
	public ResponseEntity<?> deleteuser(HttpServletRequest request, @PathVariable("userId") Integer userId) {
		log.debug("Request for delete profile");
		this.userService.deleteUser(userId, (String) request.getSession().getAttribute("Email"));
		request.getSession().setAttribute("Email", null);
		return new ResponseEntity<>(Map.of("message", "user delete successfully"), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Flux<UserDto>> getUsers(HttpServletRequest request) {
		log.debug("Request for get all users");
		return ResponseEntity.ok(this.userService.getAllUsers((String) request.getSession().getAttribute("Email")));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUsersByID(@PathVariable("userId") Integer userId) {
		log.debug("Request for get user using userId");
		return ResponseEntity.ok(this.userService.getUserbyId(userId));

	}

	@GetMapping("/userName/{name}")
	public ResponseEntity<List<String>> getUsersByName(@PathVariable("name") String userName,
			HttpServletRequest request) {
		log.debug("Request for get user using username");
		return ResponseEntity
				.ok(this.userService.getUserByName(userName, (String) request.getSession().getAttribute("Email")));
	}

}
