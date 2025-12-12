package com.cgv.CGV.service;

import com.cgv.CGV.entity.Foodpurchase;
import com.cgv.CGV.entity.Reservation;
import com.cgv.CGV.entity.User;
import com.cgv.CGV.repository.FoodPurchaseRepository;
import com.cgv.CGV.repository.ReservationRepository;
import com.cgv.CGV.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final FoodPurchaseRepository foodPurchaseRepository;

    public User getUserInfo(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<Reservation> getReservations(String userId) {
        return reservationRepository.findByUserUserId(userId);
    }

    public List<Foodpurchase> getFoodPurchases(String userId) {
        return foodPurchaseRepository.findByUserUserId(userId);
    }
}