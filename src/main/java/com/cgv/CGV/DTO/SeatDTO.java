package com.cgv.CGV.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

// ScheduleWithSeatsDto.java
@Getter
@Setter
public class SeatDTO {
    private  int rowNo;
    private  int colNo;
    private  boolean reserved; // 예약 여부 (true/false)
    private  int seatType;     // 원본 seatType (Premium, Standard 등)

    public SeatDTO(int rowNo, int colNo, boolean reserved, int seatType) {
        this.rowNo = rowNo;
        this.colNo = colNo;
        this.reserved = reserved;
        this.seatType = seatType;
    }
}
