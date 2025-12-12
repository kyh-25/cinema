package com.cgv.CGV.controller;

import com.cgv.CGV.DTO.ReservationDTO;
import com.cgv.CGV.entity.StoreId;
import com.cgv.CGV.entity.User;
import com.cgv.CGV.service.FoodOrderService;
import com.cgv.CGV.service.StoreService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;
    private final FoodOrderService orderService;

    // 1. 영화관 선택 화면
    @GetMapping("/select")
    public String selectTheater(Model model) {
        model.addAttribute("theaters", storeService.getAllTheaters());
        return "store/select-theater";
    }

    // 2. 영화관 매점 메뉴 표시
    @GetMapping("/menu")
    public String menu(
            @RequestParam int theaterId,
            Model model
    ) {
        model.addAttribute("theaterId", theaterId);
        model.addAttribute("menu", storeService.getMenuByTheater(theaterId));
        return "store/menu";
    }

    // 3. 결제 처리
    @PostMapping("/order")
    public String order(
            @RequestParam int theaterId,
            @RequestParam int foodId,
            @RequestParam int qty,
            HttpSession session
    ) {
        try {
            User user = (User) session.getAttribute("loginUser");
            orderService.purchaseFood(user, theaterId,foodId, qty);
            return "redirect:/mypage";
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }
    }
}
