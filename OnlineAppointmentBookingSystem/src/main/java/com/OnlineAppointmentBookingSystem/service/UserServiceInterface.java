package com.OnlineAppointmentBookingSystem.service;

import java.util.List;

import com.OnlineAppointmentBookingSystem.dto.LoginRequest;
import com.OnlineAppointmentBookingSystem.dto.UserRequest;
import com.OnlineAppointmentBookingSystem.entity.User;
import com.OnlineAppointmentBookingSystem.exception.EmailAlreadyExistsException;
import com.OnlineAppointmentBookingSystem.exception.UserNotFoundException;

public interface UserServiceInterface {

	User register(UserRequest userRequest) throws EmailAlreadyExistsException;
	User login(LoginRequest loginRequest) throws UserNotFoundException;
	User updateProfile(UserRequest userRequest) throws UserNotFoundException;
	List<User> getAllDoctors();
}
