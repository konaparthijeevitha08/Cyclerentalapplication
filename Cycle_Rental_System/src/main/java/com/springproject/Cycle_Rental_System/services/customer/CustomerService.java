package com.springproject.Cycle_Rental_System.services.customer;

import java.util.List;

import com.springproject.Cycle_Rental_System.dto.BookACycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDtoListDto;
import com.springproject.Cycle_Rental_System.dto.SearchCycleDto;

public interface CustomerService {
    List<CycleDto> getAllCycles();
    
    boolean bookACycle(BookACycleDto bookACycleDto);
    CycleDto getCycleById(Long cycleId);
    List<BookACycleDto> getBookingsByUserId(Long userId);
    CycleDtoListDto searchCycle(SearchCycleDto searchCycleDto);

}