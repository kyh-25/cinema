package com.cgv.CGV.controller;

import com.cgv.CGV.DTO.ReservationDTO;
import com.cgv.CGV.DTO.SeatDTO;
import com.cgv.CGV.entity.Reservation;
import com.cgv.CGV.entity.Seat;
import com.cgv.CGV.entity.User;
import com.cgv.CGV.repository.ReservationRepository;
import com.cgv.CGV.repository.ScheduleRepository;
import com.cgv.CGV.repository.SeatRepository;
import com.cgv.CGV.repository.UserRepository;
import com.cgv.CGV.service.ReservationService;
import com.cgv.CGV.service.ScheduleService;
import com.cgv.CGV.service.SeatService;
import com.cgv.CGV.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ScheduleService scheduleService;

    // 좌석 화면
    @GetMapping("/select")
    public String selectSeat(@RequestParam int scheduleId, Model model) {
        int screenId = scheduleService.getScreenIdByScheduleId(scheduleId);

        List<List<SeatDTO>> seatMap = reservationService.getSeatMap(scheduleId,screenId);

        model.addAttribute("seatMap", seatMap);
        model.addAttribute("scheduleId", scheduleId);

        return "seat/select_seat";
    }

    @PostMapping("/book")
    public String book(HttpSession session, String scheduleId, String selectedSeatRow, String selectedSeatCol,
                       Model model, RedirectAttributes redirectAttributes) {
        Integer schedule = Integer.valueOf(scheduleId);
        Integer col = Integer.valueOf(selectedSeatCol);
        Integer row = Integer.valueOf(selectedSeatRow);
        User user = (User) session.getAttribute("loginUser");
        try {
            int price = scheduleService.getPrice(schedule);
            int screen_id = scheduleService.getScreenIdByScheduleId(schedule);
            ReservationDTO reservationDto = reservationService.reserve(user,schedule,screen_id, row, col,price);
            redirectAttributes.addFlashAttribute("reservation", reservationDto);
            return "redirect:/reservation/success"; // 성공 시
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addAttribute("scheduleId", scheduleId);
            System.out.println(e.getMessage());
            return "redirect:/reservation/select";
        }
    }

    @GetMapping("/success")
    public String Success(Model model){
        ReservationDTO reservationDto = (ReservationDTO) model.getAttribute("reservation");
        System.out.println(reservationDto.getMovieTitle());
        if (reservationDto == null) {
            // 예약 정보가 없으므로 메인으로 보냅니다.
            return "redirect:/";
        }

        model.addAttribute("reservation", reservationDto);
        return "reservation/success";
    }
}
