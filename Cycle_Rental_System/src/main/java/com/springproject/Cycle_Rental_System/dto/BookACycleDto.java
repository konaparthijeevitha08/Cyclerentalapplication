package com.springproject.Cycle_Rental_System.dto;

import java.util.Date;

import com.springproject.Cycle_Rental_System.enums.BookCycleStatus;

import lombok.Data;

@Data
public class BookACycleDto {
  private Long id;
  private Date fromDate;
  private Date toDate;
  private Long days;
  private Long price;
  private BookCycleStatus bookCycleStatus;
  private Long cycleId; // Assuming card refers to a payment method, not a physical card
  private Long userId;
  private String username;
  private String email;
}