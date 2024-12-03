package com.springproject.Cycle_Rental_System.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.Cycle_Rental_System.dto.BookACycleDto;
import com.springproject.Cycle_Rental_System.dto.CycleDto;
import com.springproject.Cycle_Rental_System.dto.SearchCycleDto;
import com.springproject.Cycle_Rental_System.services.admin.AdminService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
	private final AdminService adminService;

    @PostMapping("/cycle")
    public ResponseEntity<?> postCycle(@ModelAttribute CycleDto cycleDto) throws IOException {
        boolean success = adminService.postCycle(cycleDto);

        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping("/cycles")
    public ResponseEntity<?> getAllCycles() {
        return ResponseEntity.ok(adminService.getAllCycles());
    }

    @DeleteMapping("/cycle/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
    adminService.deleteCycle(id);
    return ResponseEntity.ok().body(null);
}

    @GetMapping("/cycle/{id}")
    public ResponseEntity<CycleDto> getCycleById(@PathVariable Long id) {

        CycleDto cycleDto = adminService.getCycleById(id);
        return ResponseEntity.ok(cycleDto);

    }
    
    @PutMapping("/cycle/{cycleId}")
    public ResponseEntity<Void> updateCycle(@PathVariable Long cycleId, @ModelAttribute CycleDto cycleDto) throws IOException {
        try {
            boolean success = adminService.updateCycle(cycleId, cycleDto);
            if (success) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping("/cycle/bookings")
    public ResponseEntity<List<BookACycleDto>> getBookings() {
        return ResponseEntity.ok(adminService.getBookings());
    }
    
    @GetMapping("/cycle/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status) {
        boolean success = adminService.changeBookingStatus(bookingId, status);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    
    @PostMapping("/cycle/search")
    public ResponseEntity<?> searchCycle(@RequestBody SearchCycleDto searchCycleDto) {
        return ResponseEntity.ok(adminService.searchCycle(searchCycleDto));
    }

}
