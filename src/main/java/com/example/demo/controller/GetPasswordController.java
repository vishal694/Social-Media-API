package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.MailRequest;
import com.example.demo.bean.MailResponse;
import com.example.demo.service.IGetPasswordService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(tags = "Email", value = "Forgot Password", description = "Get your passoword")
public class GetPasswordController {

	@Autowired
	IGetPasswordService getPasswordService;

	@PostMapping("/getEmail")
	public ResponseEntity<MailResponse> sendEmail(@RequestBody MailRequest request) {
		Map<String, Object> model = new HashMap<>();
		model.put("Name", request.getName());
		model.put("location", "Bangalore,India");
		return new ResponseEntity<MailResponse>(getPasswordService.sendEmail(request, model), HttpStatus.OK);// ;

	}
}
