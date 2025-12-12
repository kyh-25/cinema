package com.cgv.CGV.controller;

import com.cgv.CGV.DTO.ScheduleForm;
import com.cgv.CGV.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/schedule")
@RequiredArgsConstructor
public class AdminScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/add")
    public String addScheduleForm(Model model) {
        model.addAttribute("movies", scheduleService.getMovies());
        model.addAttribute("screens", scheduleService.getScreens());
        model.addAttribute("form", new ScheduleForm());
        return "admin/schedule-add";
    }

    @PostMapping("/add")
    public String addScheduleSubmit(@ModelAttribute("form") ScheduleForm form,
                                    Model model) {

        try {
            scheduleService.createSchedule(form);
        } catch (Exception e) {
            model.addAttribute("movies", scheduleService.getMovies());
            model.addAttribute("screens", scheduleService.getScreens());
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/schedule-add";
        }

        return "redirect:/admin/schedule/add?success=1";
    }
}
