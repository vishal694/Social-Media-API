package com.example.demo.payloads;

import com.example.demo.bean.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileDto {
	private String id;

	private String fileName;

	private String fileType;
	
	private byte[] data;
	
	private Post image;
}
