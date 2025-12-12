package com.cgv.CGV.repository;

import com.cgv.CGV.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByScreenId(int screenId);
    Optional<Seat> findByScreenIdAndIdRowNoAndIdColNo(
            Integer screenId,
            Integer rowNo,
            Integer colNo
    );
    boolean existsByIdRowNoAndIdColNoAndScreenId(int rowNo, int colNo, int screenId);
}