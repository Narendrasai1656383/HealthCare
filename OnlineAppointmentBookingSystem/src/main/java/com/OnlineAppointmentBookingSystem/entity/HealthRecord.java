package com.OnlineAppointmentBookingSystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HealthRecord {
	@jakarta.persistence.Id
	@GeneratedValue
	private Long healthRecordId;
	public Long getHealthRecordId() {
		return healthRecordId;
	}
	public void setHealthRecordId(Long healthRecordId) {
		this.healthRecordId = healthRecordId;
	}
	private String diagnosis;
	private String prescription;
	private LocalDateTime date=LocalDateTime.now();
	@ManyToOne
	@JoinColumn(name = "patient_id")
	@JsonIgnore
	private User patient;
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public User getPatient() {
		return patient;
	}
	public void setPatient(User patient) {
		this.patient = patient;
	}
}
