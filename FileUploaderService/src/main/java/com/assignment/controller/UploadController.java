package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.service.UploaderService;

@Controller
public class UploadController {
	
	@Autowired
	UploaderService uploaderService;

	@PostMapping(value = "/upload",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		if (uploaderService.uploadFile(file))
			return "File Uploader Successfully";
		else
			return "file upload failed due to some reason";
	}
	
	@GetMapping(value = "/")
	public String welcomePage() {
		return "fileUpload";
	}
}
