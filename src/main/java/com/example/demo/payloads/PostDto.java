package com.example.demo.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String postTitle;
	
	private String content;
	
	private String imagName;

	private Date addDate;

	
	private CategoryDto category;
	
	private UserDto user;
}
