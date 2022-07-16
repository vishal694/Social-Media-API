package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.UserRequestDto;
import com.example.demo.service.IUserRequestService;

@RestController
@RequestMapping("/api/request")
public class RequestController {

	@Autowired
	private IUserRequestService userRequestService;

	@PostMapping("/send")
	public ResponseEntity<UserRequestDto> sendUserRequest(@RequestBody UserRequestDto userRequestDto,
			HttpServletRequest request) {
		UserRequestDto userRequestDtoRequest = userRequestService.saveRequest(userRequestDto,
				(String) request.getSession().getAttribute("Email"));
		return new ResponseEntity<>(userRequestDtoRequest, HttpStatus.CREATED);
	}

	@GetMapping("/received")
	public ResponseEntity<List<String>> getReceivedRequests(HttpServletRequest request) {
		return ResponseEntity
				.ok(userRequestService.getReceviedRequests((String) request.getSession().getAttribute("Email")));

	}

	@GetMapping("/requested")
	public ResponseEntity<List<String>> getSendRequests(HttpServletRequest request) {
		return ResponseEntity
				.ok(userRequestService.getsendRequests((String) request.getSession().getAttribute("Email")));

	}

	@PutMapping("/accept")
	public ResponseEntity<String> acceptRequest(@RequestBody UserRequestDto userRequestDto,
			HttpServletRequest request) {
		return ResponseEntity.ok(
				userRequestService.acceptRequest(userRequestDto, (String) request.getSession().getAttribute("Email")));

	}

}
