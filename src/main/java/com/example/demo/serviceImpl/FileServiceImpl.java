package com.example.demo.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.IfileService;

@Service
public class FileServiceImpl implements IfileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// get file name
		String fileName = file.getOriginalFilename();

		String randomId = UUID.randomUUID().toString();
		String newFileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
		// full path
		String filePath = path + File.separator + newFileName;

		// create folder
		File f = new File(path);

		if (!f.exists()) {
			f.mkdir();
		}

		// file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return newFileName;

	}

	@Override
	public InputStream getResource(String path, String fileName) throws IOException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);

		return is;
	}

}
