package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface IfileService {
	
	String uploadImage(String path,MultipartFile file) throws IOException;
	InputStream getResource(String path ,String fileName) throws IOException;
}
