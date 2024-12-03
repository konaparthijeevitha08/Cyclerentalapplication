package com.springproject.Cycle_Rental_System.services.admin;

import java.io.IOException;
import java.util.List;

import com.springproject.Cycle_Rental_System.dto.BookACycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDtoListDto;
import com.springproject.Cycle_Rental_System.dto.SearchCycleDto;

public interface AdminService {
	boolean postCycle(CycleDto cycleDto);
	List<CycleDto> getAllCycles();
	void deleteCycle(Long id);
	CycleDto getCycleById(Long id);
	boolean updateCycle(Long cycleId, CycleDto cycleDto) throws IOException;
	List<BookACycleDto> getBookings();
	boolean changeBookingStatus(Long bookingId, String status);
	CycleDtoListDto searchCycle(SearchCycleDto searchCycleDto);


}
