package com.cgv.CGV.service;

import com.cgv.CGV.entity.Foodpurchase;
import com.cgv.CGV.entity.Store;
import com.cgv.CGV.entity.StoreId;
import com.cgv.CGV.entity.User;
import com.cgv.CGV.repository.FoodPurchaseRepository;
import com.cgv.CGV.repository.StoreRepository;
import com.cgv.CGV.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FoodOrderService {

    private final UserRepository userRepo;
    private final StoreRepository storeRepo;
    private final FoodPurchaseRepository foodPurchaseRepo;

    @Transactional
    public void purchaseFood(User user, int theaterID,int foodId, int qty) {
        StoreId storeId = new StoreId();
        storeId.setFoodId(foodId);
        storeId.setTheaterId(theaterID);
        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store item not found"));
        System.out.println(store.getStock());
        System.out.println(store.getFood().getName());
        System.out.println(store.getFood().getPrice());
        System.out.println(qty);
        int totalPrice = store.getFood().getPrice() * qty;

        if (user.getBalance() < totalPrice) {
            throw new RuntimeException("Insufficient balance");
        }
        if (store.getStock() < qty) {
            throw new RuntimeException("Out of stock");
        }

        // 잔액 차감
        user.setBalance(user.getBalance() - totalPrice);
        userRepo.save(user);

//        // 재고 감소
//        store.setStock(store.getStock() - qty);
//        storeRepo.save(store);

        // 구매 기록 생성
        Foodpurchase purchase = new Foodpurchase();
        purchase.setUser(user);
        purchase.setStore(store);
        purchase.setAmount(qty);
        purchase.setDate(LocalDate.now());
        foodPurchaseRepo.save(purchase);
    }
}
