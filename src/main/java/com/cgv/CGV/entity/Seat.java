package com.cgv.CGV.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @EmbeddedId
    private SeatId id;

    @MapsId("screenId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @Column(name = "seat_type")
    private Integer seatType;

    public Seat() {
    }
    public Seat(Screen screen, Integer rowNo, Integer colNo, Integer seatType) {
        this.screen = screen;
        this.id = new SeatId(rowNo, colNo, screen.getId());
        this.seatType = seatType;
    }

    public SeatId getId() {
        return id;
    }

    public void setId(SeatId id) {
        this.id = id;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }


}