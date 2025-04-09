package com.OnlineAppointmentBookingSystem.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OnlineAppointmentBookingSystem.entity.User;
import com.OnlineAppointmentBookingSystem.exception.NoLoggedInUserException;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class LoginAspect {
	@Autowired
	private HttpSession httpSession;
	@Pointcut("@annotation(com.OnlineAppointmentBookingSystem.aspect.RequiredLogin)")
	public void requiredLoginPointCut() {
		
	}
	@Before("requiredLoginPointCut()")
	public void checkLogin() throws NoLoggedInUserException {
		if(httpSession.getAttribute("userLoggedIn")==null) {
			throw new NoLoggedInUserException("No user is logged in!");
		}
		String loggedInUser=((User)httpSession.getAttribute("userLoggedIn")).getName();
		if(loggedInUser==null) {
			throw new NoLoggedInUserException("No user is logged in!");
		}
	}
}
