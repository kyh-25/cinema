package com.cgv.CGV.controller;

import com.cgv.CGV.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/screens")
@RequiredArgsConstructor
public class AdminScreenController {

    private final ScreenService screenService;

    @GetMapping("/add")
    public String addScreenForm(Model model) {
        model.addAttribute("theaters", screenService.getAllTheaters());
        return "admin/screen-add";
    }

    @PostMapping("/add")
    public String addScreen(
            @RequestParam Integer theaterId,
            @RequestParam String name,
            @RequestParam Integer rows,
            @RequestParam Integer cols
    ) {
        screenService.createScreen(theaterId, name, rows, cols);
        return "redirect:/admin/screens/add?success=1";
    }
}
