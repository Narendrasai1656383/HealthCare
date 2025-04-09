package com.OnlineAppointmentBookingSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.OnlineAppointmentBookingSystem.exception.EmailAlreadyExistsException;
import com.OnlineAppointmentBookingSystem.exception.NoLoggedInUserException;
import com.OnlineAppointmentBookingSystem.exception.UserNotFoundException;

@RestControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler(NoLoggedInUserException.class)
	public ResponseEntity<String> noLoggedInUserException(NoLoggedInUserException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String> emailAlreadyExistsException(EmailAlreadyExistsException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(UserNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
