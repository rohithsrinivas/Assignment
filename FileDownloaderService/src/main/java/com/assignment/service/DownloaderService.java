package com.assignment.service;

import java.io.File;
import java.util.List;

import org.springframework.core.io.Resource;

public interface DownloaderService {
	
	List<File> loadAllFiles();
	
	Resource getFileForDownload(String fileName);

}
