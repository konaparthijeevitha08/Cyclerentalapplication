package com.springproject.Cycle_Rental_System.dto;

import com.springproject.Cycle_Rental_System.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private UserRole userRole;
    private Long userId;
}