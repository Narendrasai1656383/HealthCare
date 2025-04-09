package com.OnlineAppointmentBookingSystem.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
	@jakarta.persistence.Id
	@GeneratedValue
	private Long appointmentId;
	private LocalDateTime date=LocalDateTime.now();
	private String timeSlot;
	private String status;
	@ManyToOne
	@JoinColumn(name="patient_id")
	@JsonIgnore
	private User patient;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	@JsonIgnore
	private User doctor;
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getPatient() {
		return patient;
	}
	public void setPatient(User patient) {
		this.patient = patient;
	}
	public User getDoctor() {
		return doctor;
	}
	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
}
