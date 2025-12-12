package com.cgv.CGV.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ScheduleForm {

    private Integer movieId;
    private Integer screenId;
    private LocalDate date;
    private LocalTime startTime;
    private Integer price;

    // getters and setters
}
