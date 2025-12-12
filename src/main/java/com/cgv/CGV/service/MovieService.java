package com.cgv.CGV.service;

import com.cgv.CGV.DTO.MovieForm;
import com.cgv.CGV.entity.*;
import com.cgv.CGV.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final ReservationRepository reservationRepository;
    private final MovieRepository movieRepository;
    private final MoviegenreRepository moviegenreRepository;
    private final DirectorRepository directorRepository;
    private final ReviewRepository reviewRepository;
    private final MoviegenreRepository movieGenreRepository;


    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public Optional<Movie> getByID(int movieId) {
        return movieRepository.findById(movieId);
    }
    public List<Movie> getPopularMoviesOfThisWeek() {

        LocalDate today = LocalDate.now();
        LocalDate befoWeek = today.minusDays(7);
        LocalDate nextDay = today.plusDays(1);

        // 1) 인기 영화 movie_id 리스트 조회
        List<Map<String, Object>> popular = reservationRepository.findPopularMovies(befoWeek, nextDay);

        // 2) movie_id → Movie 엔티티로 변환
        List<Movie> result = new ArrayList<>();
        for (Map<String, Object> row : popular) {
            Integer movieId = (Integer) row.get("movieId");
            movieRepository.findById(movieId).ifPresent(result::add);
        }

        return result;
    }
    public List<Movie> getMoviesScheduledNextMonth() {
        return movieRepository.findMoviesWithScheduleNextMonth();
    }

    public List<Moviegenre> getGenres(int movieId) {
        return moviegenreRepository.findGenresByMovieId(movieId);
    }

    public List<Director> getDirectors(int movieId) {
        return directorRepository.findDirectorsByMovieId(movieId);
    }

    public List<Review> getReviews(int movieId) {
        return reviewRepository.findReviewsByMovieIdWithUser(movieId);
    }

    @Transactional
    public Movie createMovie(MovieForm form) {

        // 1. 영화 저장
        Movie movie = new Movie();
        movie.setTitle(form.getTitle());
        movie.setDescription(form.getDescription());
        movie.setReleaseDate(form.getReleaseDate());
        movie.setRunningTime(form.getRunningTime());
        movie.setAgeRating(form.getAgeRating());
        movie.setDistributor(form.getDistributor());

        Movie savedMovie = movieRepository.save(movie);

        // 2. 장르 저장
        for (String g : form.getGenres()) {
            if (!g.isBlank()) {
                Moviegenre mg = new Moviegenre();
                MoviegenreId id = new MoviegenreId(savedMovie.getId(), g);
                mg.setId(id);
                mg.setMovie(savedMovie);
                movieGenreRepository.save(mg);
            }
        }

        // 3. 감독 저장
        for (String d : form.getDirectors()) {
            if (!d.isBlank()) {
                Director director = new Director();
                DirectorId id = new DirectorId(savedMovie.getId(), d);
                director.setId(id);
                director.setMovie(savedMovie);
                directorRepository.save(director);
            }
        }

        return savedMovie;
    }
}

