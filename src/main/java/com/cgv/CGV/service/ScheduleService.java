package com.cgv.CGV.service;
import com.cgv.CGV.DTO.ScheduleForm;
import com.cgv.CGV.DTO.ScheduleWithSeatsDto;
import com.cgv.CGV.entity.Movie;
import com.cgv.CGV.entity.Schedule;
import com.cgv.CGV.entity.Screen;
import com.cgv.CGV.entity.Theater;
import com.cgv.CGV.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScreenRepository screenRepository;
    private final ReservationRepository reservationRepository;
    private final MovieRepository movieRepository;

    public List<Theater> getTheatersByMovieAndDate(int movieId, LocalDate date) {
        return scheduleRepository.findTheatersByMovieAndDate(movieId, date);
    }

    public int getScreenIdByScheduleId(int schduleId) {
        return scheduleRepository.findScreenById(schduleId).getScreen().getId();
    }

    public Schedule getScheduleById(int schduleId) {
        return scheduleRepository.findById(schduleId).get();
    }

    public int getPrice(int scheduleId){
        return  scheduleRepository.findById(scheduleId).get().getPrice();
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public List<Screen> getScreens() {
        return screenRepository.findAll();
    }

    public List<ScheduleWithSeatsDto> getSchedules(int movieId, int theaterId, LocalDate date) {
        // 1. 해당 조건의 Schedule 리스트 조회
        List<Schedule> schedules = scheduleRepository.findSchedules(movieId, theaterId, date);

        List<ScheduleWithSeatsDto> dtoList = new ArrayList<>();

        for (Schedule schedule : schedules) {
            // 2. 해당 Schedule의 Theater 정보 조회 (전체 좌석 수 획득)
            Optional<Screen> totalSeats = screenRepository.findById(schedule.getScreen().getId());

            // 3. 해당 ScheduleId에 대한 Reservation 개수 카운트 (예매된 좌석 수 획득)
            int reservedSeats = reservationRepository.countByScheduleId(schedule.getId());

            // 4. DTO 생성 및 리스트에 추가
            ScheduleWithSeatsDto dto = new ScheduleWithSeatsDto();
            dto.setId(schedule.getId());
            dto.setStartTime(schedule.getStartTime());
            dto.setEndTime(schedule.getEndTime());
            // ... Schedule의 다른 필드 설정
            dto.setTotalSeats(totalSeats.get().getTotalSeats());
            dto.setReservedSeats(reservedSeats);

            dtoList.add(dto);
        }

        return dtoList;
    }
    @Transactional
    public Schedule createSchedule(ScheduleForm form) {

        Movie movie = movieRepository.findById(form.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid movieId"));

        Screen screen = screenRepository.findById(form.getScreenId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid screenId"));

        Schedule schedule = new Schedule();
        schedule.setMovie(movie);      // Movie ManyToOne
        schedule.setScreen(screen);    // Screen ManyToOne
        schedule.setDate(form.getDate());
        schedule.setStartTime(form.getStartTime());
        schedule.setEndTime(form.getStartTime());
        schedule.setPrice(form.getPrice());

        return scheduleRepository.save(schedule);
    }

    public List<LocalDate> getAvailableDates(int movieId){
        return scheduleRepository.findDistinctDatesByMovieId(movieId);
    }
}

