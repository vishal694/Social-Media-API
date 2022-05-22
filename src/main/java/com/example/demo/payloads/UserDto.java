package com.example.demo.payloads;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String about;
	private Set<CommentDto> userComments = new HashSet<CommentDto>();
}
