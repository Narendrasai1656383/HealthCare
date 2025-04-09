package com.OnlineAppointmentBookingSystem.service;
import java.util.List;
import java.util.Optional;

import com.OnlineAppointmentBookingSystem.dto.AppointmentRequest;
import com.OnlineAppointmentBookingSystem.entity.Appointment;

public interface AppointmentServiceInterface {
	Appointment bookAppointment(AppointmentRequest appointmentRequest);
	List<Appointment> getAppointmentsByUserId(Long patientId);
	Optional<Appointment> getById(Long id);
	Appointment updateStatus(Long id,String newStatus);
}
