package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.UserRequestDto;
import com.example.demo.service.IUserRequestService;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/request")
@Api(tags = "Requests", value = "FriendsRequests", description = "Friends Requests Operation")
public class RequestController {

	Logger log = LoggerFactory.getLogger(RequestController.class);
	
	@Autowired
	private IUserRequestService userRequestService;

	@PostMapping("/send")
	
	public ResponseEntity<UserRequestDto> sendUserRequest(@RequestBody UserRequestDto userRequestDto,
			HttpServletRequest request) {
		log.debug("Request for send request");
		UserRequestDto userRequestDtoRequest = userRequestService.saveRequest(userRequestDto,
				(String) request.getSession().getAttribute("Email"));
		return new ResponseEntity<>(userRequestDtoRequest, HttpStatus.CREATED);
	}

	@GetMapping("/received")
	public ResponseEntity<List<String>> getReceivedRequests(HttpServletRequest request) {
		log.debug("Request for check received user friend requests");
		return ResponseEntity
				.ok(userRequestService.getReceviedRequests((String) request.getSession().getAttribute("Email")));

	}

	@GetMapping("/requested")
	public ResponseEntity<List<String>> getSendRequests(HttpServletRequest request) {
		log.debug("Request for sended user requests");
		return ResponseEntity
				.ok(userRequestService.getsendRequests((String) request.getSession().getAttribute("Email")));

	}

	@PutMapping("/accept")
	public ResponseEntity<String> acceptRequest(@RequestBody UserRequestDto userRequestDto,
			HttpServletRequest request) {
		log.debug("Request for accept requests");
		return ResponseEntity.ok(
				userRequestService.acceptRequest(userRequestDto, (String) request.getSession().getAttribute("Email")));

	}

	@DeleteMapping("/reject")
	public ApiResponse reject(@RequestBody UserRequestDto userRequestDto, HttpServletRequest request) {
		this.userRequestService.rejectRequest(userRequestDto, (String) request.getSession().getAttribute("Email"));
		return new ApiResponse("post is successfully deleted", true);
	}

}
