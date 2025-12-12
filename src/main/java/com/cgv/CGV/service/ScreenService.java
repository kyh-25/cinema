package com.cgv.CGV.service;

import com.cgv.CGV.entity.Screen;
import com.cgv.CGV.entity.Seat;
import com.cgv.CGV.entity.Theater;
import com.cgv.CGV.repository.ScreenRepository;
import com.cgv.CGV.repository.SeatRepository;
import com.cgv.CGV.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterRepository theaterRepository;
    private final SeatRepository seatRepository;

    @Transactional
    public Screen createScreen(Integer theaterId, String name, int rows, int cols) {

        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid theater ID"));

        int totalSeats = rows * cols;

        Screen screen = new Screen();
        screen.setTheater(theater);
        screen.setName(name);
        screen.setTotalSeats(totalSeats);

        Screen savedScreen = screenRepository.save(screen);

        // 좌석 생성
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                Seat seat = new Seat(savedScreen, r, c, 0); // seatType=0 기본
                seatRepository.save(seat);
            }
        }

        return savedScreen;
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }
}
