package com.cgv.CGV.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "foodpurchase")
public class Foodpurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "food_id", referencedColumnName = "food_id", nullable = false),
            @JoinColumn(name = "theater_id", referencedColumnName = "theater_id", nullable = false)
    })
    private Store store;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "date")
    private Instant date;

}