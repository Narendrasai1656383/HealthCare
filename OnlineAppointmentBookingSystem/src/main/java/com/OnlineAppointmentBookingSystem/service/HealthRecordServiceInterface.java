package com.OnlineAppointmentBookingSystem.service;

import java.util.List;
import java.util.Optional;

import com.OnlineAppointmentBookingSystem.dto.HealthRecordRequest;
import com.OnlineAppointmentBookingSystem.entity.HealthRecord;

public interface HealthRecordServiceInterface {
	HealthRecord uploadRecord(HealthRecordRequest healthRecordRequest);
	List<HealthRecord> getRecordsByPatient(Long patientId);
	Optional<HealthRecord> getRecordById(Long id);
	void deleteRecord(Long id);
}
