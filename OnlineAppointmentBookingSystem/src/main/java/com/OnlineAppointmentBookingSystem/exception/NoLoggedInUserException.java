package com.OnlineAppointmentBookingSystem.exception;

public class NoLoggedInUserException extends Exception{
	private static final long serialVersionUID = 1L;

	public  NoLoggedInUserException(String s) {
		super(s);
	}
}

