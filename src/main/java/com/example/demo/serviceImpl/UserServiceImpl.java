package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.payloads.UserDto;
import com.example.demo.repo.IUserRepo;
import com.example.demo.service.IUserService;
import com.example.demo.exception.ResourceNotException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createuser(UserDto userdto) {
		
		User user = this.dtoToUser(userdto);
		User savedUser = this.userRepo.save(user);
		return this.userTODto(savedUser);
	}

	@Override
	public UserDto updateuser(UserDto userdto ,Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotException("User","User Id",userId));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		UserDto userDto1  = this.userTODto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserbyId(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotException("User","User Id",userId));
		return this.userTODto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User>list = this.userRepo.findAll();
		List<UserDto> userdtos = list.stream().map(user -> this.userTODto(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		this.userRepo.findById(userId).orElseThrow(()->new ResourceNotException("user", "User Id", userId));
		this.userRepo.deleteById(userId);
		
	}

	private User dtoToUser(UserDto userdto) {
		User user = this.modelMapper.map(userdto, User.class);
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		return user;
	}
	
	private UserDto userTODto(User user) {
		UserDto userdto = this.modelMapper.map(user,UserDto.class);;
		
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());
		
		return userdto;
		
	}

}
