package com.OnlineAppointmentBookingSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineAppointmentBookingSystem.dto.HealthRecordRequest;
import com.OnlineAppointmentBookingSystem.entity.HealthRecord;
import com.OnlineAppointmentBookingSystem.entity.User;
import com.OnlineAppointmentBookingSystem.repository.HealthRecordRepository;
import com.OnlineAppointmentBookingSystem.service.HealthRecordServiceInterface;

import jakarta.servlet.http.HttpSession;
@Service
public class HealthRecordService implements HealthRecordServiceInterface {
	@Autowired
	private HealthRecordRepository healthRecordRepository;
	@Autowired
	private HttpSession httpSession;
	@Override
	public HealthRecord uploadRecord(HealthRecordRequest healthRecordRequest) {
		HealthRecord healthRecord=new HealthRecord();
		healthRecord.setDiagnosis(healthRecordRequest.getDiagnosis());
		healthRecord.setPrescription(healthRecordRequest.getPrescription());
		healthRecord.setPatient((User)httpSession.getAttribute("userLoggedIn"));
		return healthRecordRepository.save(healthRecord);
	}

	@Override
	public List<HealthRecord> getRecordsByPatient(Long patientId) {
		return healthRecordRepository.findByPatientId(patientId);
	}

	@Override
	public Optional<HealthRecord> getRecordById(Long id) {
		return healthRecordRepository.findById(id);
	}

	@Override
	public void deleteRecord(Long id) {
		healthRecordRepository.deleteById(id);
	}

}
