package com.example.demo.service;

import java.util.List;

import com.example.demo.payloads.UserDto;

import reactor.core.publisher.Flux;

public interface IUserService {

	UserDto createuser(UserDto userdto);

	UserDto updateuser(UserDto userdto, Integer userId);

	UserDto getUserbyId(Integer userId);

	List<String> getUserByName(String name, String email);

	Flux<UserDto> getAllUsers(String email);

	void deleteUser(Integer userId, String email);

	UserDto registerNewUser(UserDto user);

	boolean checkRole(String email);

	Flux<UserDto> convertListToFlux(List<UserDto> userDto);

}
