package com.assignment.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exception.UserNotRegisteredException;
import com.assignment.model.User;

public interface UserService {

	User createUser(User user) throws UserNotRegisteredException;
	
	boolean isUserValid(String username,String password);

	String uploadFile(MultipartFile file);
	
	Resource downloadFile(String fileName);
	
}
