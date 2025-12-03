package com.cgv.CGV.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "screen_id", referencedColumnName = "screen_id", nullable = false),
            @JoinColumn(name = "row_no", referencedColumnName = "row_no", nullable = false),
            @JoinColumn(name = "col_no", referencedColumnName = "col_no", nullable = false)
    })
    private Seat seat;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "date")
    private Instant date;

}