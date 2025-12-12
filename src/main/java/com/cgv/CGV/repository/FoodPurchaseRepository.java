package com.cgv.CGV.repository;

import com.cgv.CGV.entity.Foodpurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodPurchaseRepository extends JpaRepository<Foodpurchase, Integer> {

    List<Foodpurchase> findByUserUserId(String userId);
}