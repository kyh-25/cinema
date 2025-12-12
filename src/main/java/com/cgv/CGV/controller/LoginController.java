package com.cgv.CGV.controller;

import com.cgv.CGV.entity.User;
import com.cgv.CGV.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "login/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(String userId, String pw, Model model, HttpSession session) {

        User loginUser = userService.login(userId, pw);

        if (loginUser == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login/login";
        }

        // 로그인 성공 → 세션 저장
        session.setAttribute("loginUser", loginUser);

        return "redirect:/";  // 메인 페이지로 이동
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 만료
        return "redirect:/";
    }
}
