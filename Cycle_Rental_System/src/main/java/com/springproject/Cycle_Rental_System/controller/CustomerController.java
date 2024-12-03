package com.springproject.Cycle_Rental_System.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.Cycle_Rental_System.dto.BookACycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDto;
import com.springproject.Cycle_Rental_System.dto.SearchCycleDto;
import com.springproject.Cycle_Rental_System.services.customer.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/cycles")
    public ResponseEntity<List<CycleDto>> getAllCycles() {
        List<CycleDto> cycleDtoList = customerService.getAllCycles();
        return ResponseEntity.ok(cycleDtoList);
    }
    
    @PostMapping("/cycle/book")
    public ResponseEntity<Void> bookACycle(@RequestBody BookACycleDto bookACycleDto) {
      boolean success = customerService.bookACycle(bookACycleDto);

      if (success) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
      }

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/cycle/{cycleId}")
    public ResponseEntity<CycleDto> getCycleById(@PathVariable Long cycleId){
	    CycleDto cycleDto = customerService.getCycleById(cycleId);
	    if(cycleDto == null) return ResponseEntity.notFound().build();
	    return ResponseEntity.ok(cycleDto);
	}
    
    @GetMapping("/cycle/bookings/{userId}")
    public ResponseEntity<List<BookACycleDto>> getBookingsByUserId(@PathVariable Long userId){
    	return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }

    @PostMapping("/cycle/search")
    public ResponseEntity<?> searchCycle(@RequestBody SearchCycleDto searchCycleDto) {
        return ResponseEntity.ok(customerService.searchCycle(searchCycleDto));
    }

}
