package com.OnlineAppointmentBookingSystem.dto;

public class HealthRecordRequest {
	private String diagnosis;
	private String prescription;
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
}
