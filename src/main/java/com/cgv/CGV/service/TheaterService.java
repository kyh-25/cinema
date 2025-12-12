package com.cgv.CGV.service;

import com.cgv.CGV.entity.Theater;
import com.cgv.CGV.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }
}
