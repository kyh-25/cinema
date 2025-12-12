package com.cgv.CGV.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

// ScheduleWithSeatsDto.java
@Getter
@Setter
public class ScheduleWithSeatsDto {
    private Integer id;
    private LocalTime startTime;
    private LocalTime endTime;

    // 추가할 정보
    private int totalSeats; // 상영관의 전체 좌석 수 (Theater 엔티티에서 가져옴)
    private long reservedSeats; // 예매된 좌석 수 (Reservation 테이블에서 카운트)

}