package com.cgv.CGV.api;

import com.cgv.CGV.DTO.ScheduleWithSeatsDto;
import com.cgv.CGV.entity.Schedule;
import com.cgv.CGV.entity.Theater;
import com.cgv.CGV.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleApiController {

    private final ScheduleService scheduleService;

    @GetMapping("/theaters")
    public List<Theater> getTheaters(
            @RequestParam int movieId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return scheduleService.getTheatersByMovieAndDate(movieId, date);
    }

    @GetMapping("/list")
    public List<ScheduleWithSeatsDto> getSchedules(
            @RequestParam int movieId,
            @RequestParam int theaterId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return scheduleService.getSchedules(movieId, theaterId, date);
    }
}

