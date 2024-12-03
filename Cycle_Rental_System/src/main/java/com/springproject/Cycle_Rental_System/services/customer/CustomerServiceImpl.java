package com.springproject.Cycle_Rental_System.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.springproject.Cycle_Rental_System.dto.BookACycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDtoListDto;
import com.springproject.Cycle_Rental_System.dto.SearchCycleDto;
import com.springproject.Cycle_Rental_System.entity.BookACycle;
import com.springproject.Cycle_Rental_System.entity.Cycle;
import com.springproject.Cycle_Rental_System.entity.User;
import com.springproject.Cycle_Rental_System.enums.BookCycleStatus;
import com.springproject.Cycle_Rental_System.repository.BookACycleRepository;
import com.springproject.Cycle_Rental_System.repository.CycleRepository;
import com.springproject.Cycle_Rental_System.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CycleRepository cycleRepository;
	private final UserRepository userRepository;
	private final BookACycleRepository bookACycleRepository;

	@Override
	public List<CycleDto> getAllCycles() {
		// TODO Auto-generated method stub
		return cycleRepository.findAll().stream().map(Cycle::getCycleDto).collect(Collectors.toList());
	}
	
	@Override
	public boolean bookACycle(BookACycleDto bookACycleDto) {
	  Optional<Cycle> optionalCycle = cycleRepository.findById(bookACycleDto.getCycleId());
	  Optional<User> optionalUser = userRepository.findById(bookACycleDto.getUserId());

	  if (optionalCycle.isPresent() && optionalUser.isPresent()) {
	    Cycle existingCycle = optionalCycle.get();

	    BookACycle bookACycle = new BookACycle();
	    bookACycle.setUser(optionalUser.get());
	    bookACycle.setCycle(existingCycle);
	    bookACycle.setBookCycleStatus(BookCycleStatus.PENDING);
	    
	    // Set fromDate and toDate
        bookACycle.setFromDate(bookACycleDto.getFromDate());
        bookACycle.setToDate(bookACycleDto.getToDate());

	    long diffInMilliseconds = bookACycleDto.getToDate().getTime() - bookACycleDto.getFromDate().getTime();
	    long days = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);

	    bookACycle.setDays(days);
	    bookACycle.setPrice(existingCycle.getPrice() * days);

	    bookACycleRepository.save(bookACycle);
	    return true;
	  }
	  return false;
	}

	@Override
	public CycleDto getCycleById(Long cycleId) {
		// TODO Auto-generated method stub
		Optional<Cycle> optionalCycle = cycleRepository.findById(cycleId);
		return optionalCycle.map(Cycle::getCycleDto).orElse(null);
	}

	@Override
	public List<BookACycleDto> getBookingsByUserId(Long userId) {
		return bookACycleRepository.findAllByUserId(userId).stream().map(BookACycle::getBookACycleDto).collect(Collectors.toList());
	}

	@Override
	public CycleDtoListDto searchCycle(SearchCycleDto searchCycleDto) {
		// TODO Auto-generated method stub
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
