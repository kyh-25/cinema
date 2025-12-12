package com.cgv.CGV.DTO;

import lombok.Getter;
import lombok.Setter;

// ScheduleWithSeatsDto.java
@Getter
@Setter
public class ReservationDTO {
    private  Integer reservationId;
    private  String movieTitle;
    private  String theaterName;
    private  String dateTime;
    private  String seatInfo;
    private  Integer amount;

    public ReservationDTO(Integer id, String title, String name, String s, String s1, Integer amount) {
        this.reservationId = id;
        this.movieTitle = title;
        this.theaterName = name;
        this.dateTime = s;
        this.seatInfo = s1;
        this.amount = amount;
    }
}
