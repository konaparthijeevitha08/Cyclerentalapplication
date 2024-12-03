package com.springproject.Cycle_Rental_System.entity;


import java.util.Date;

import com.springproject.Cycle_Rental_System.dto.CycleDto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cycles")
public class Cycle {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    
    private String color;

    
    private String name;

    
    private String type;

    private String transmission;
    
    private String description;

    
    private Long price;
    

    
    private Date year;
    
    @Column(columnDefinition = "longblob")
    private byte[] image;
    
    public CycleDto getCycleDto() {
        CycleDto cycleDto = new CycleDto();

        cycleDto.setId(id);
        cycleDto.setName(name);
        cycleDto.setBrand(brand);
        cycleDto.setColor(color);
        cycleDto.setPrice(price);
        cycleDto.setDescription(description);
        cycleDto.setType(type);
        cycleDto.setTransmission(transmission);
        cycleDto.setYear(year);
        cycleDto.setReturnedImage(image);

        return cycleDto;
    }
}
