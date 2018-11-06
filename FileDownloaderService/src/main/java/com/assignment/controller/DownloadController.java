package com.assignment.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.service.DownloaderService;

@RestController
public class DownloadController {

	@Autowired
	DownloaderService downloaderService;

	@GetMapping("/getAll")
	public List<File> listAllFiles() {
		List<File> loadAllFiles = downloaderService.loadAllFiles();
		loadAllFiles.stream().forEach(System.out::println);
		return loadAllFiles;
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
		Resource downloadableResource = downloaderService.getFileForDownload(fileName);
		return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(downloadableResource);
	}

}
