package com.cgv.CGV.controller;

import com.cgv.CGV.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private  MovieService movieService;

    @GetMapping("/movies")
    public String movieList(Model model) {
        this.movieService = new MovieService();
        model.addAttribute("movies", movieService.getAllMovies());

        return "movie/list"; // templates/movie/list.html
    }
}