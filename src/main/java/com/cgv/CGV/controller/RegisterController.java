package com.cgv.CGV.controller;

import com.cgv.CGV.entity.User;
import com.cgv.CGV.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/register")
    public String registerForm() {
        return "login/register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(User user, Model model) {

        boolean result = userService.register(user);

        if (!result) {
            model.addAttribute("error", "이미 존재하는 아이디입니다.");
            return "login/register";
        }

        return "redirect:/login";  // 가입 후 로그인 페이지로 이동
    }
}