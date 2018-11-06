package com.assignment.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DownloaderServiceImpl implements DownloaderService {

	@Value("${folder.uploader.base.path}")
	String basePathForUploadedFiles;

	@Override
	public List<File> loadAllFiles() {
		File baseFolder = new File(basePathForUploadedFiles);
		File[] uploadedFiles = baseFolder.listFiles();
		return Arrays.asList(uploadedFiles);
	}

	@Override
	public Resource getFileForDownload(String fileName) {
		File file = new File(basePathForUploadedFiles + fileName);
		Path fullFilePath = Paths.get(file.getAbsolutePath());
		ByteArrayResource fileInByteArray = null;
		try {
			fileInByteArray = new ByteArrayResource(Files.readAllBytes(fullFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileInByteArray;
	}

}
