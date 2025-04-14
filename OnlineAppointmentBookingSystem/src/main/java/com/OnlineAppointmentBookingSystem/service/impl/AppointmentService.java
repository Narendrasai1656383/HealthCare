package com.OnlineAppointmentBookingSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineAppointmentBookingSystem.dto.AppointmentRequest;
import com.OnlineAppointmentBookingSystem.entity.Appointment;
import com.OnlineAppointmentBookingSystem.entity.User;
import com.OnlineAppointmentBookingSystem.repository.AppointmentRepository;
import com.OnlineAppointmentBookingSystem.repository.UserRepository;
import com.OnlineAppointmentBookingSystem.service.AppointmentServiceInterface;

import jakarta.servlet.http.HttpSession;
@Service
public class AppointmentService implements AppointmentServiceInterface{
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpSession httpSession;
	@Override
	public Appointment bookAppointment(AppointmentRequest appointmentRequest) {
		Appointment appointment=new Appointment();
		appointment.setDoctor((userRepository.findById(appointmentRequest.getDoctorId())).get());
		appointment.setTimeSlot(appointmentRequest.getTimeSlot());
		appointment.setPatient((User)httpSession.getAttribute("userLoggedIn"));
		appointment.setStatus("PENDING");
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> getAppointmentsByUserId(Long id) {
		User user=(userRepository.findById(id)).get();
		if(user.getRole().equals("DOCTOR")) {
			return appointmentRepository.findByDoctor(user);
		}
		return appointmentRepository.findByPatient(user);
	}

	@Override
	public Optional<Appointment> getById(Long id) {
		return appointmentRepository.findById(id);
	}

	@Override
	public Appointment updateStatus(Long id, String newStatus) {
		Appointment appt=(appointmentRepository.findById(id)).get();
	    appt.setStatus(newStatus);
		return appointmentRepository.save(appt);
	}
}
