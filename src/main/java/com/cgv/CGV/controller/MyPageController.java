package com.cgv.CGV.controller;

import com.cgv.CGV.entity.Foodpurchase;
import com.cgv.CGV.entity.Reservation;
import com.cgv.CGV.entity.User;
import com.cgv.CGV.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService mypageService;

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        String userId = loginUser.getUserId();

        User userInfo = mypageService.getUserInfo(userId);
        List<Reservation> reservations = mypageService.getReservations(userId);
        List<Foodpurchase> foodPurchases = mypageService.getFoodPurchases(userId);

        model.addAttribute("user", userInfo);
        model.addAttribute("reservations", reservations);
        model.addAttribute("foods", foodPurchases);

        return "mypage/mypage";
    }
}