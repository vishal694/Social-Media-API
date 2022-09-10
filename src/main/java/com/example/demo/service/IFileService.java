package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bean.LoadFile;

public interface IFileService {

	String uploadImage(String path, MultipartFile file) throws IOException;

	InputStream getResource(String path, String fileName) throws IOException;

	public String addFile(MultipartFile upload) throws IOException;
	
	public LoadFile downloadFile(String id) throws IOException;
}
