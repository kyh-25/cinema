package com.cgv.CGV.repository;
import com.cgv.CGV.entity.Schedule;
import com.cgv.CGV.entity.Screen;
import com.cgv.CGV.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    // 특정 날짜 + 영화에 해당하는 상영이 있는 영화관 목록
    @Query("SELECT DISTINCT s.screen.theater FROM Schedule s " +
            "WHERE s.movie.id = :movieId AND s.date = :date")
    List<Theater> findTheatersByMovieAndDate(
            @Param("movieId") int movieId,
            @Param("date") LocalDate date
    );

    // 특정 영화 + 특정 극장 + 특정 날짜의 상영 일정
    @Query("SELECT s FROM Schedule s " +
            "WHERE s.movie.id= :movieId AND s.screen.theater.id = :theaterId " +
            "AND s.date = :date ORDER BY s.startTime")
    List<Schedule> findSchedules(
            @Param("movieId") int movieId,
            @Param("theaterId") int theaterId,
            @Param("date") LocalDate date
    );

    @Query("""
    SELECT DISTINCT s.date
    FROM Schedule s
    WHERE s.movie.id = :movieId
    AND s.date >= CURRENT_DATE()
    ORDER BY s.date ASC
    """)
    List<LocalDate> findDistinctDatesByMovieId(@Param("movieId") int movieId);

    Schedule findScreenById(int schduleId);
}
