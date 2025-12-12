package com.cgv.CGV.service;

import com.cgv.CGV.entity.Store;
import com.cgv.CGV.entity.Theater;
import com.cgv.CGV.repository.StoreRepository;
import com.cgv.CGV.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final TheaterRepository theaterRepo;
    private final StoreRepository storeRepo;

    public List<Theater> getAllTheaters() {
        return theaterRepo.findAll();
    }

    public List<Store> getMenuByTheater(int theaterId) {
        return storeRepo.findByTheaterId(theaterId);
    }
}

