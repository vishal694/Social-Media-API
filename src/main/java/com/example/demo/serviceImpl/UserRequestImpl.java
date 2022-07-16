package com.example.demo.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.UsersRequest;
import com.example.demo.payloads.UserRequestDto;
import com.example.demo.repo.IUserRequestRepo;
import com.example.demo.service.IUserRequestService;

@Service
public class UserRequestImpl implements IUserRequestService {

	@Autowired
	private IUserRequestRepo userRequestRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserRequestDto saveRequest(UserRequestDto userRequestDto, String email) {

		if ((userRequestRepo.vaildateRequestedOrNot(userRequestDto.getFromEmail(), email)).equals("FALSE")) {
			userRequestDto.setRequestFlag(false);
			int getToId = userRequestRepo.findIdByEmail(email);
			userRequestDto.setToRequestId(getToId);
			userRequestDto.setToEmail(email);
			int getfromId = userRequestRepo.findIdByEmail(userRequestDto.getFromEmail());
			if (getfromId != getToId) {
				userRequestDto.setFromRequesId(getfromId);
				UsersRequest usersRequest = dtoToUserRequest(userRequestDto);
				UsersRequest usersRequestResult = this.userRequestRepo.save(usersRequest);
				return this.userRequestTODto(usersRequestResult);
			}

		}
		return null;
	}
	
	@Override
	public String acceptRequest(UserRequestDto userRequestDto, String email) {
		Integer row = userRequestRepo.acceptRequest(email, userRequestDto.getToEmail());
		if(row==1) {
			return "accepted";
		}
		return "notAccepted";
	}


	@Override
	public List<String> getReceviedRequests(String email) {
		return userRequestRepo.getUserRequests(email);
	}

	@Override
	public List<String> getsendRequests(String email) {
		return userRequestRepo.getSendUserRequests(email);
	}

	private UsersRequest dtoToUserRequest(UserRequestDto userRequestDto) {
		UsersRequest usersRequest = this.modelMapper.map(userRequestDto, UsersRequest.class);
		return usersRequest;
	}

	private UserRequestDto userRequestTODto(UsersRequest usersRequest) {
		UserRequestDto userRequestDto = this.modelMapper.map(usersRequest, UserRequestDto.class);
		return userRequestDto;
	}

}
