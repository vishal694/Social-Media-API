package com.example.demo.service;

import java.util.List;

import com.example.demo.payloads.UserRequestDto;

public interface IUserRequestService {
	
	UserRequestDto saveRequest(UserRequestDto userRequestDto,String email);
	
	List<String> getReceviedRequests(String email);
	
	List<String> getsendRequests(String email);
	
	String acceptRequest(UserRequestDto userRequestDto,String userEmail);
	
	void rejectRequest(UserRequestDto userRequestDto,String userEmail);
}
