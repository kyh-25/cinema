package com.cgv.CGV.controller;
import com.cgv.CGV.entity.Theater;
import com.cgv.CGV.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/theater")
public class AdminTheaterController {

    private final TheaterService theaterService;

    // 영화관 추가 폼
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("theater", new Theater());
        return "admin/theater-add";
    }

    // 영화관 등록 처리
    @PostMapping("/add")
    public String addTheater(@ModelAttribute("theater") Theater theater) {

        theaterService.createTheater(theater);

        return "redirect:/admin/theater/list";
    }

    // 등록된 영화관 리스트 보기
    @GetMapping("/list")
    public String theaterList(Model model) {
        model.addAttribute("theaters", theaterService.findAll());
        return "admin/theater-list";
    }
}
