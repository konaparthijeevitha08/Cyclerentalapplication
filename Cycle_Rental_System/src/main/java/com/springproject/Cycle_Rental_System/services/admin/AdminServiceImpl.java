package com.springproject.Cycle_Rental_System.services.admin;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import java.io.IOException;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.springproject.Cycle_Rental_System.dto.BookACycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDtoListDto;
import com.springproject.Cycle_Rental_System.dto.SearchCycleDto;
import com.springproject.Cycle_Rental_System.entity.BookACycle;
import com.springproject.Cycle_Rental_System.entity.Cycle;
import com.springproject.Cycle_Rental_System.enums.BookCycleStatus;
import com.springproject.Cycle_Rental_System.repository.BookACycleRepository;
import com.springproject.Cycle_Rental_System.repository.CycleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final CycleRepository cycleRepository;
	private final BookACycleRepository bookACycleRepository;

	@Override
	public boolean postCycle(CycleDto cycleDto) {
		try {
	        Cycle cycle = new Cycle();
	        cycle.setName(cycleDto.getName());
	        cycle.setBrand(cycleDto.getBrand());
	        cycle.setColor(cycleDto.getColor());
	        cycle.setPrice(cycleDto.getPrice());
	        cycle.setYear(cycleDto.getYear());
	        cycle.setType(cycleDto.getType());
			cycle.setTransmission(cycleDto.getTransmission());
	        cycle.setDescription(cycleDto.getDescription());
	        cycle.setTransmission(cycleDto.getTransmission());
	        cycle.setImage(cycleDto.getImage().getBytes());
			
	        cycleRepository.save(cycle);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	@Override
	public List<CycleDto> getAllCycles() {
	    return cycleRepository.findAll().stream().map(Cycle::getCycleDto).collect(Collectors.toList());
	}

	@Override
	public void deleteCycle(Long id) {
		cycleRepository.deleteById(id);
		
		
	}

	@Override
	public CycleDto getCycleById(Long id) {
	  Optional<Cycle> optionalCycle = cycleRepository.findById(id);
	  return optionalCycle.map(Cycle::getCycleDto).orElse(null);
	
	}
	
	@Override
	public boolean updateCycle(Long cycleId, CycleDto cycleDto) throws IOException{
	    Optional<Cycle> optionalCycle = cycleRepository.findById(cycleId);
	    if (optionalCycle.isPresent()) {
	        Cycle existingCycle = optionalCycle.get();
	        if (cycleDto.getImage() != null) {
	            existingCycle.setImage(cycleDto.getImage().getBytes());
	        }
	        existingCycle.setPrice(cycleDto.getPrice());
	        existingCycle.setYear(cycleDto.getYear());
	        existingCycle.setType(cycleDto.getType());
	        existingCycle.setDescription(cycleDto.getDescription());
	        existingCycle.setTransmission(cycleDto.getTransmission());
	        existingCycle.setColor(cycleDto.getColor());
	        existingCycle.setName(cycleDto.getName());
	        existingCycle.setBrand(cycleDto.getBrand());
	        cycleRepository.save(existingCycle);
	        return true;
	    } else {
	        return false;
	    }
	}

	@Override
	public List<BookACycleDto> getBookings() {
		return bookACycleRepository.findAll().stream().map(BookACycle::getBookACycleDto).collect(Collectors.toList());
	}
	
	@Override
	public boolean changeBookingStatus(Long bookingId, String status) {
	    Optional<BookACycle> optionalBookACycle = bookACycleRepository.findById(bookingId);
	    if (optionalBookACycle.isPresent()) {
	        BookACycle existingBookACycle = optionalBookACycle.get();
	        if (Objects.equals(status, "Approve"))
	            existingBookACycle.setBookCycleStatus(BookCycleStatus.APPROVED);
	        else
	            existingBookACycle.setBookCycleStatus(BookCycleStatus.REJECTED);
	        bookACycleRepository.save(existingBookACycle);
	        return true;
	    }
	    return false;
	}

	
	@Override
	public CycleDtoListDto searchCycle(SearchCycleDto searchCycleDto) {
	    Cycle cycle = new Cycle();
	    cycle.setBrand(searchCycleDto.getBrand());
	    cycle.setType(searchCycleDto.getType());
	    cycle.setTransmission(searchCycleDto.getTransmission());
	    cycle.setColor(searchCycleDto.getColor());
	    
	    ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
	        .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
	        .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
	        .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
	        .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
	    
	    Example<Cycle> cycleExample = Example.of(cycle, exampleMatcher);
	    List<Cycle> cycleList = cycleRepository.findAll(cycleExample);
	    
	    CycleDtoListDto cycleDtoListDto = new CycleDtoListDto();
	    cycleDtoListDto.setCycleDtoList(cycleList.stream().map(Cycle::getCycleDto).collect(Collectors.toList()));
	    
	    return cycleDtoListDto;
	}

	


}
