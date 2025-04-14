package com.OnlineAppointmentBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OnlineAppointmentBookingSystem.entity.Appointment;
import com.OnlineAppointmentBookingSystem.entity.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByPatient(User patient);
	List<Appointment> findByDoctor(User doctor);
}
