package com.assignment.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(UserNotRegisteredException.class)
	public String userNotRegisteredException(UserNotRegisteredException exception) {
		return exception.getMessage();
	}
}
