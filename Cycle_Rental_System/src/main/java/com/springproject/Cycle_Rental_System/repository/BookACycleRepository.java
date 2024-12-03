package com.springproject.Cycle_Rental_System.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproject.Cycle_Rental_System.dto.CycleDto;
import com.springproject.Cycle_Rental_System.entity.BookACycle;

@Repository
public interface BookACycleRepository extends JpaRepository<BookACycle, Long> {

	List<BookACycle> findAllByUserId(Long userId);
}


