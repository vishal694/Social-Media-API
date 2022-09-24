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

import com.example.demo.bean.LoadFile;
import com.example.demo.service.IFileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;


@Service
public class FileServiceImpl implements IFileService {

//	@Async

	@Autowired
	private GridFsTemplate template;
	
	@Autowired
    private GridFsOperations operations;

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

//	@Async
	@Override
	public InputStream getResource(String path, String fileName) throws IOException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);

		return is;
	}

	@Override
	public String addFile(MultipartFile upload) throws IOException {
		DBObject metadata = new BasicDBObject();
		metadata.put("fileSize", upload.getSize());

		Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(),
				metadata);

		return fileID.toString();
	}

	@Override
	public LoadFile downloadFile(String id) throws IOException {
		GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );
		
		LoadFile loadFile = new LoadFile();
		if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setFilename( gridFSFile.getFilename() );

            loadFile.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }
		return loadFile;
	}
}
