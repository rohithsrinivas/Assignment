package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exception.UserNotRegisteredException;
import com.assignment.model.User;
import com.assignment.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String welcomePage() {
		return "welcome";
	}

	@ResponseBody
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User registerUser(@RequestBody User user) throws UserNotRegisteredException {
		return userService.createUser(user);
	}

	@GetMapping(value = "/validate/{username}/{password}")
	@ResponseBody
	public String checkUser(@PathVariable(name = "username", required = true) String username,
			@PathVariable(name = "password", required = true) String password) {
		if (userService.isUserValid(username, password))
			return "User Valid";
		return "Invalid Credentials";
	}
	
	@PostMapping(value = "/fileUpload")
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		return userService.uploadFile(file);
	}
	
	@GetMapping(value = "/fileDownload/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
		Resource downloadFile = userService.downloadFile(fileName);
		return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(downloadFile);
	}
}
