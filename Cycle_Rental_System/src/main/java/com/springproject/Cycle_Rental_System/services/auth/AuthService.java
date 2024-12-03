package com.springproject.Cycle_Rental_System.services.auth;

import com.springproject.Cycle_Rental_System.dto.SignupRequest;
import com.springproject.Cycle_Rental_System.dto.UserDto;

public interface AuthService {
	
	UserDto createCustomer(SignupRequest signupRequest);
	
	boolean hasCustomerWithEmail(String email);

}
