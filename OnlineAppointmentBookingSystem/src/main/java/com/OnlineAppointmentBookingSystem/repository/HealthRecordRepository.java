package com.OnlineAppointmentBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OnlineAppointmentBookingSystem.entity.HealthRecord;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
	List<HealthRecord> findByPatientId(Long patientId);
}
