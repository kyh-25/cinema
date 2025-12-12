package com.cgv.CGV.controller;

import com.cgv.CGV.entity.Movie;
import com.cgv.CGV.service.MovieService;
import com.cgv.CGV.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final  MovieService movieService;
    private final ScheduleService scheduleService;

    @GetMapping("/movies")
    public String movieList(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());

        return "movie/list"; // templates/movie/list.html
    }

    @GetMapping("/movie/{id}")
    public String detail(@PathVariable("id") int movieId, Model model) {
        Movie movie = movieService.getByID(movieId)
                .orElseThrow(() -> new RuntimeException("영화 없음"));
        model.addAttribute("movie", movie);

        List<LocalDate> availableDates = scheduleService.getAvailableDates(movieId);
        model.addAttribute("availableDates", availableDates);

        model.addAttribute("movieGenres", movieService.getGenres(movieId));
        model.addAttribute("directors", movieService.getDirectors(movieId));
        model.addAttribute("reviews", movieService.getReviews(movieId));
        return "movie/movie_detail_page";  // canvas에 생성된 Thymeleaf 페이지
    }
}