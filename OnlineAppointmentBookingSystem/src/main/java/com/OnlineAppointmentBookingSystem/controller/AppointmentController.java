package com.OnlineAppointmentBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineAppointmentBookingSystem.aspect.RequiredLogin;
import com.OnlineAppointmentBookingSystem.dto.AppointmentRequest;
import com.OnlineAppointmentBookingSystem.entity.Appointment;
import com.OnlineAppointmentBookingSystem.entity.User;
import com.OnlineAppointmentBookingSystem.service.AppointmentServiceInterface;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	private AppointmentServiceInterface appoiAppointmentService;
	@Autowired
	private HttpSession httpSession;
	@PostMapping("/book")
	@RequiredLogin
	public ResponseEntity<Appointment> book(@RequestBody AppointmentRequest appointmentRequest){
		return ResponseEntity.ok(appoiAppointmentService.bookAppointment(appointmentRequest));
	}
	@GetMapping("/all")
	@RequiredLogin
	public List<Appointment> getByPatient(){
		return appoiAppointmentService.getAppointmentsByUserId(((User)(httpSession.getAttribute("userLoggedIn"))).getId());
	}
	@SuppressWarnings("unchecked")
	@PutMapping("/{id}/{status}")
	@RequiredLogin
	public ResponseEntity<Appointment> updateStatus(@PathVariable(name = "id") Long id,@PathVariable(name = "status") String status) {
		if(!((User)httpSession.getAttribute("userLoggedIn")).getRole().equals("DOCTOR")) {
			return (ResponseEntity<Appointment>) ResponseEntity.badRequest();
		}
		return ResponseEntity.ok(appoiAppointmentService.updateStatus(id, status));
	}
}
