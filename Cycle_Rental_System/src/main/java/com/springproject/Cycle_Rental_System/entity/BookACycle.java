package com.springproject.Cycle_Rental_System.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springproject.Cycle_Rental_System.dto.BookACycleDto;
import com.springproject.Cycle_Rental_System.enums.BookCycleStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class BookACycle {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	
	  
	  private Date fromDate;
	  private Date toDate;
	  private Long days;
	  private Long price;
	  private BookCycleStatus bookCycleStatus;
	
	  @ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "user_id", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  @JsonIgnore
	  private User user;
	
	  @ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "cycle_id", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  @JsonIgnore
	  private Cycle cycle;
	
	  public BookACycleDto getBookACycleDto() {
		    BookACycleDto bookACycleDto = new BookACycleDto();
		    bookACycleDto.setId(id);
		    bookACycleDto.setDays(days);
		    bookACycleDto.setBookCycleStatus(bookCycleStatus);
		    bookACycleDto.setPrice(price);
		    bookACycleDto.setToDate(toDate);
		    bookACycleDto.setFromDate(fromDate);
		    bookACycleDto.setEmail(user.getEmail());
		    bookACycleDto.setUsername(user.getName());
		    bookACycleDto.setUserId(user.getId());
		    bookACycleDto.setCycleId(cycle.getId());
		    return bookACycleDto;
		}
}
