package com.cgv.CGV.repository;

import com.cgv.CGV.entity.Director;
import com.cgv.CGV.entity.Movie;
import com.cgv.CGV.entity.Moviegenre;
import com.cgv.CGV.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // @Query 어노테이션을 사용하여 쿼리 정의
    @Query(value = "SELECT DISTINCT m.* FROM Movie m " +
            "JOIN Schedule s ON m.movie_id = s.movie_id " +
            "WHERE s.date BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL 1 MONTH) ",
            nativeQuery = true)
    List<Movie> findMoviesWithScheduleNextMonth();

    @Query("SELECT mg FROM Moviegenre mg WHERE mg.movie.id = :movieId")
    List<Moviegenre> findGenresByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT d FROM Director d WHERE d.movie.id = :movieId")
    List<Director> findDirectorsByMovieId(@Param("movieId") Integer movieId);

    @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.movie.id = :movieId ORDER BY r.createdAt DESC")
    List<Review> findReviewsByMovieIdWithUser(@Param("movieId") Integer movieId);
}

