package com.assignment.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploaderService {

	boolean uploadFile(MultipartFile file);
}
