package com.OnlineAppointmentBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineAppointmentBookingSystem.aspect.RequiredLogin;
import com.OnlineAppointmentBookingSystem.dto.LoginRequest;
import com.OnlineAppointmentBookingSystem.dto.UserRequest;
import com.OnlineAppointmentBookingSystem.entity.User;
import com.OnlineAppointmentBookingSystem.exception.EmailAlreadyExistsException;
import com.OnlineAppointmentBookingSystem.exception.UserNotFoundException;
import com.OnlineAppointmentBookingSystem.service.UserServiceInterface;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServiceInterface userService;
	@Autowired
	private HttpSession httpSession;
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody UserRequest userRequest) throws EmailAlreadyExistsException{
		return new ResponseEntity<>(userService.register(userRequest),HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) throws UserNotFoundException{
		User user=userService.login(loginRequest);
		httpSession.setAttribute("userLoggedIn", user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@GetMapping("/logout")
	@com.OnlineAppointmentBookingSystem.aspect.RequiredLogin
	public ResponseEntity<String> logoutUser(HttpSession httpSession) {
		httpSession.invalidate();
		return new ResponseEntity<String>("Logged Out Successfully",HttpStatus.OK);
	}
	@PostMapping("/update")
	@RequiredLogin
	public ResponseEntity<User> updateProfile(@RequestBody UserRequest userRequest) throws UserNotFoundException{
		return new ResponseEntity<User>(userService.updateProfile(userRequest), HttpStatus.OK);
	}
	@GetMapping("/doctors")
	@RequiredLogin
	public List<User> getAllDoctors(){
		return userService.getAllDoctors();
	}
}
