package com.assignment.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploaderServiceImpl implements UploaderService {
	
	/* I am uploading file into my local file system directory*/
	@Value("${folder.uploader.base.path}")
	String folderBasePath;

	@Override
	public boolean uploadFile(MultipartFile file) {
		if(file.isEmpty()) {
			return false;
		}
		
		try {
			byte[] fileInBytes = file.getBytes();
			Path filePath = Paths.get(folderBasePath + file.getOriginalFilename());
			Files.write(filePath, fileInBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
