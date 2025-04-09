package com.OnlineAppointmentBookingSystem.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	private String phone;
	private String gender;
	@OneToMany(mappedBy = "patient")
	private List<Appointment> patientAppointments;
	@OneToMany(mappedBy = "doctor")
	private List<Appointment> doctorAppointments;
	@OneToMany(mappedBy = "patient")
	private List<HealthRecord> record;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Appointment> getPatientAppointments() {
		return patientAppointments;
	}
	public void setPatientAppointments(List<Appointment> patientAppointments) {
		this.patientAppointments = patientAppointments;
	}
	public List<Appointment> getDoctorAppointments() {
		return doctorAppointments;
	}
	public void setDoctorAppointments(List<Appointment> doctorAppointments) {
		this.doctorAppointments = doctorAppointments;
	}
	public List<HealthRecord> getRecord() {
		return record;
	}
	public void setRecord(List<HealthRecord> record) {
		this.record = record;
	}
}
