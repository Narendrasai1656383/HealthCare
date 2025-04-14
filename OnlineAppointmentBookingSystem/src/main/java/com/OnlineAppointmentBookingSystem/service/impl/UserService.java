package com.OnlineAppointmentBookingSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineAppointmentBookingSystem.dto.LoginRequest;
import com.OnlineAppointmentBookingSystem.dto.UserRequest;
import com.OnlineAppointmentBookingSystem.entity.User;
import com.OnlineAppointmentBookingSystem.exception.EmailAlreadyExistsException;
import com.OnlineAppointmentBookingSystem.exception.UserNotFoundException;
import com.OnlineAppointmentBookingSystem.repository.UserRepository;
import com.OnlineAppointmentBookingSystem.service.UserServiceInterface;
@Service
public class UserService implements UserServiceInterface {
	@Autowired
	private UserRepository userRepository;
	@Override
	public User register(UserRequest userRequest) throws EmailAlreadyExistsException {
		if(userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		User user =new User();
		user.setEmail(userRequest.getEmail());
		user.setName(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setGender(userRequest.getGender());
		user.setRole(userRequest.getRole());
		user.setPhone(userRequest.getPhone());
		return userRepository.save(user);
	}
	@Override
	public User login(LoginRequest loginRequest) throws UserNotFoundException {
		User user=(userRepository.findByEmail(loginRequest.getEmail())).get();
		if(user==null || !user.getPassword().equals(loginRequest.getPassword())) {
			throw new UserNotFoundException("Invalid Credentials");
		}
		return user;
	}
	@Override
	public User updateProfile(UserRequest userRequest) throws UserNotFoundException {
		Optional<User> user=userRepository.findByEmail(userRequest.getEmail());
		if(user.isEmpty()) {
			throw new UserNotFoundException("User Not found to update");
		}
		user.get().setName(userRequest.getName());
		user.get().setPassword(userRequest.getPassword());
		user.get().setGender(userRequest.getGender());
		user.get().setRole(userRequest.getRole());
		user.get().setPhone(userRequest.getPhone());
		return userRepository.save(user.get());
	}
	@Override
	public List<User> getAllDoctors() {
		return userRepository.findByRole("DOCTOR");
	}
	@Override
	public User getPatientDetails(Long id) {
		return userRepository.findById(id).get();
	}
}
