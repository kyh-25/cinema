package com.cgv.CGV.repository;

import com.cgv.CGV.entity.Reservation;
import com.cgv.CGV.entity.Seat;
import com.cgv.CGV.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
        SELECT r.schedule.movie.id AS movieId, COUNT(r) AS cnt
        FROM Reservation r
        WHERE r.date >= :start AND r.date < :end
        GROUP BY r.schedule.movie.id
        ORDER BY cnt DESC
    """)
    List<Map<String, Object>> findPopularMovies(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    List<Reservation> findByScheduleId(int scheduleId);

    int countByScheduleId(Integer scheduleId);

    List<Reservation> findByUserUserId(String userUserId);

    boolean existsByScheduleIdAndSeatIdScreenIdAndSeatIdRowNoAndSeatIdColNo(
            Integer scheduleId,
            Integer screenId,
            Integer rowNo,
            Integer colNo
    );

}
