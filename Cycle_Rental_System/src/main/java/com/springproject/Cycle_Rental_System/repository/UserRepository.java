package com.springproject.Cycle_Rental_System.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproject.Cycle_Rental_System.entity.User;
import com.springproject.Cycle_Rental_System.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findFirstByEmail(String email);

	User findByUserRole(UserRole admin);

}
