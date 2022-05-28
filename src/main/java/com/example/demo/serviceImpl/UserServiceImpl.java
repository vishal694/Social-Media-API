package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.commons.AppConstants;
import com.example.demo.payloads.UserDto;
import com.example.demo.repo.IRoleRepo;
import com.example.demo.repo.IUserRepo;
import com.example.demo.service.IUserService;
import com.example.demo.exception.ResourceNotException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IRoleRepo roleRepo;

	@Async
	@Override
	public UserDto createuser(UserDto userdto) {

		User user = this.dtoToUser(userdto);
		User savedUser = this.userRepo.save(user);
		return this.userTODto(savedUser);
	}

	@Async
	@Override
	public UserDto updateuser(UserDto userdto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotException("User", "User Id", userId));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());

		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userTODto(updateUser);
		return userDto1;
	}

	@Async
	@Override
	public UserDto getUserbyId(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotException("User", "User Id", userId));
		return this.userTODto(user);
	}

	@Override
	public List<UserDto> getAllUsers(String email) {
		List<User> list = this.userRepo.findAll();
		List<UserDto> userdtos = checkRole(email)
				? list.stream().map(user -> this.userTODto(user)).collect(Collectors.toList())
				: null;
		return userdtos;

	}

	@Override
	public void deleteUser(Integer userId, String email) {
		if (checkRole(email)) {
			this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotException("user", "User Id", userId));
			this.userRepo.deleteById(userId);
		}

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
		UserDto userdto = this.modelMapper.map(user, UserDto.class);
		;

//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());

		return userdto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public boolean checkRole(String email) {
		if (userRepo.findRoleByEmail(email).equals(AppConstants.ADMIN_ROLE)) {
			return true;
		}
		return false;
	}

}
