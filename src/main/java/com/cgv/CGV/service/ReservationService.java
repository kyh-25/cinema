package com.cgv.CGV.service;

import com.cgv.CGV.DTO.ReservationDTO;
import com.cgv.CGV.DTO.SeatDTO;
import com.cgv.CGV.entity.*;
import com.cgv.CGV.repository.ReservationRepository;
import com.cgv.CGV.repository.ScheduleRepository;
import com.cgv.CGV.repository.SeatRepository;
import com.cgv.CGV.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public Schedule getSchedule(int scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }

    public List<List<SeatDTO>> getSeatMap(int scheduleId, int screenId) {
        List<Seat> allSeats = seatRepository.findByScreenId(screenId);
        List<Reservation> reservations = reservationRepository.findByScheduleId(scheduleId);

        // 1. 예약된 SeatId Set 준비 (이전과 동일)
        Set<SeatId> reservedSeatIds = reservations.stream()
                .map(Reservation::getSeat)
                .map(Seat::getId)
                .collect(Collectors.toSet());

        // 최대 row 계산
        int maxRow = allSeats.stream().mapToInt(seat -> seat.getId().getRowNo()).max().orElse(0);

        // 2. 최종 DTO 2차원 리스트 생성
        List<List<SeatDTO>> SeatMapDto = new ArrayList<>();

        for (int r = 1; r <= maxRow; r++) {
            int row = r;

            List<SeatDTO> rowSeatsDto =
                    allSeats.stream()
                            .filter(s -> s.getId().getRowNo() == row)
                            .sorted(Comparator.comparingInt(s -> s.getId().getColNo()))
                            // 3. Seat 엔티티를 SeatStatusDto로 변환
                            .map(seat -> {
                                boolean isReserved = reservedSeatIds.contains(seat.getId());

                                return new SeatDTO(
                                        seat.getId().getRowNo(),
                                        seat.getId().getColNo(),
                                        isReserved,
                                        seat.getSeatType() // 원본 seatType 사용
                                );
                            })
                            .toList();

            SeatMapDto.add(rowSeatsDto);
        }
        return SeatMapDto; // 반환 타입 변경
    }

    // 예약 생성
    @Transactional
    public ReservationDTO reserve(
            User user,
            Integer scheduleId,
            Integer screenId,
            Integer row,
            Integer col,
            Integer price) {

        if (scheduleId == null || screenId == null || row == null || col == null || price == null) {
            throw new RuntimeException("예약에 필요한 정보가 누락되었습니다.");
        }

        boolean isValid = seatRepository.existsByIdRowNoAndIdColNoAndScreenId(row, col, screenId);
        //if 문을 사용하여 유효하지 않을 때(false) 예외 발생
        if (!isValid) {
            // RuntimeException을 던져 트랜잭션 롤백 유도
            throw new RuntimeException("유효하지 않은 좌석입니다.");
        }

        boolean isReservation = reservationRepository.existsByScheduleIdAndSeatIdScreenIdAndSeatIdRowNoAndSeatIdColNo(scheduleId, row, col, screenId);
        if (isReservation) {
            throw new RuntimeException("유효하지 않은 좌석입니다.");
        }

        //유저 잔액 차감
        if (user.getBalance() < price) {
            throw new RuntimeException("Insufficient balance");
        }
        user.setBalance(user.getBalance() - price);

        // 포인트 적립: 구매금액의 5% 가정
        user.setPoint(user.getPoint() + (int)(price * 0.05));
        userRepository.save(user);

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("해당 스케줄을 찾을 수 없습니다."));
        Seat seat = seatRepository.findByScreenIdAndIdRowNoAndIdColNo(screenId, row, col)
                .orElseThrow(() -> new RuntimeException("선택된 좌석 정보를 찾을 수 없습니다."));
        // 예매 생성
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setSchedule(schedule);
        reservation.setSeat(seat);
        reservation.setAmount(price);
        reservation.setDate(LocalDate.now());
        reservationRepository.save(reservation);

        ReservationDTO reservationDto = new ReservationDTO(
                reservation.getId(),
                reservation.getSchedule().getMovie().getTitle(),
                reservation.getSchedule().getScreen().getName(),
                reservation.getSchedule().getDate().toString() + ": " + reservation.getSchedule().getStartTime().toString() + "-" + reservation.getSchedule().getEndTime().toString(),
                reservation.getSeat().getId().getRowNo() + "행 " + reservation.getSeat().getId().getColNo() + "열",
                reservation.getAmount()
        );

        return reservationDto;
    }
}
