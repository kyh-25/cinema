package com.cgv.CGV.controller;

import com.cgv.CGV.entity.Movie;
import com.cgv.CGV.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MovieService movieService;

    @GetMapping("/")
    public String home(Model model) {
        List<Movie> popularMovies = movieService.getPopularMoviesOfThisWeek();
        List<Movie> movies = movieService.getMoviesScheduledNextMonth();

        model.addAttribute("popularMovies", popularMovies);
        model.addAttribute("movies", movies);
        return "index";   // home.html 로 이동
    }

}

