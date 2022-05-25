package com.example.demo.service;

import java.util.List;

import com.example.demo.payloads.UserDto;

public interface IUserService {

	UserDto createuser(UserDto userdto);
	
	UserDto updateuser(UserDto userdto , Integer userId);
	
	UserDto getUserbyId(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

	
}
