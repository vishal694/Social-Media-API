package com.example.demo.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class LoadFile {
	private String filename;
	private String fileType;
	private String fileSize;
	private byte[] file;

}
