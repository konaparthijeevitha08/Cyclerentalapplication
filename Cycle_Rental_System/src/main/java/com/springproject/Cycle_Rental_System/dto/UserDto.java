package com.springproject.Cycle_Rental_System.dto;

import com.springproject.Cycle_Rental_System.enums.UserRole;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
	private Long id;
	private String name;
	private String email;
	private UserRole userRole;
	
	

}
