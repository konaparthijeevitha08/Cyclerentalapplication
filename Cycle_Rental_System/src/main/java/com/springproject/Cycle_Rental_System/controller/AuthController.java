package com.springproject.Cycle_Rental_System.controller;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.springproject.Cycle_Rental_System.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.Cycle_Rental_System.dto.AuthenticationRequest;
import com.springproject.Cycle_Rental_System.dto.AuthenticationResponse;
import com.springproject.Cycle_Rental_System.dto.SignupRequest;
import com.springproject.Cycle_Rental_System.dto.UserDto;
import com.springproject.Cycle_Rental_System.repository.UserRepository;
import com.springproject.Cycle_Rental_System.services.auth.AuthService;
import com.springproject.Cycle_Rental_System.services.jwt.UserService;
import com.springproject.Cycle_Rental_System.utils.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final JWTUtil jwtutil;
	private final UserRepository userRepository;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signuprequest) {
		if (authService.hasCustomerWithEmail(signuprequest.getEmail())) {
            return new ResponseEntity<>("Email is already in use. Please choose another one.", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdCustomerDto = authService.createCustomer(signuprequest);
        if(createdCustomerDto == null) {
        	return new ResponseEntity<>("Customer not created, Come again Later",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }
	
	@PostMapping("/login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException, DisabledException, UsernameNotFoundException {
	    try {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
	    } catch (BadCredentialsException e) {
	        throw new BadCredentialsException("Incorrect username or password");
	    }

	    final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
	    Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

	    final String jwt = jwtutil.generateToken(userDetails);

	    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
	    if (optionalUser.isPresent()) {
	        authenticationResponse.setJwt(jwt);
	        authenticationResponse.setUserId(optionalUser.get().getId());
	        authenticationResponse.setUserRole(optionalUser.get().getUserRole());
	    }

	    return authenticationResponse;
	}
}
