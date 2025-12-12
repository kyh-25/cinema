package com.cgv.CGV.controller;

import com.cgv.CGV.DTO.MovieForm;
import com.cgv.CGV.entity.Movie;
import com.cgv.CGV.repository.MovieRepository;
import com.cgv.CGV.repository.MoviegenreRepository;
import com.cgv.CGV.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/admin/movie")
@RequiredArgsConstructor
public class AdminMovieController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("form", new MovieForm());
        return "admin/movie-create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("form") MovieForm form) {
        movieService.createMovie(form);
        return "redirect:/admin/movie/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        // 목록 출력용
        List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "admin/movie-list";
    }
}
