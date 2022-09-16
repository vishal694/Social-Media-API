package com.example.demo.bean;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@ApiModel
public class LoadFile {
	private String filename;
	private String fileType;
	private String fileSize;
	private byte[] file;

}
