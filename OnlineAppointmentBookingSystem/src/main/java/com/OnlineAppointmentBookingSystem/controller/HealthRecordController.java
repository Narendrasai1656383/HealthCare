package com.OnlineAppointmentBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineAppointmentBookingSystem.aspect.RequiredLogin;
import com.OnlineAppointmentBookingSystem.dto.HealthRecordRequest;
import com.OnlineAppointmentBookingSystem.entity.HealthRecord;
import com.OnlineAppointmentBookingSystem.entity.User;
import com.OnlineAppointmentBookingSystem.service.HealthRecordServiceInterface;

import jakarta.servlet.http.HttpSession;
@RestController
@RequestMapping("/healthrecord")
public class HealthRecordController {
	@Autowired
	private HealthRecordServiceInterface healthRecordService;
	@Autowired
	private HttpSession httpSession;
	@PostMapping("/upload")
	@RequiredLogin
	public ResponseEntity<HealthRecord> upload(@RequestBody HealthRecordRequest healthRecordRequest){
		return ResponseEntity.ok(healthRecordService.uploadRecord(healthRecordRequest));
	}
	@GetMapping("/patient")
	@RequiredLogin
	public List<HealthRecord> getByPaient(){
		return healthRecordService
				.getRecordsByPatient(((User)httpSession.getAttribute("userLoggedIn")).getId());
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		healthRecordService.deleteRecord(id);
		return ResponseEntity.ok("Health record deleted successfully");
	}
}
