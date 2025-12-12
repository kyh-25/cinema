package com.cgv.CGV.repository;
import com.cgv.CGV.entity.Review;
import com.cgv.CGV.entity.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
    @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.movie.id = :movieId ORDER BY r.createdAt DESC")
    List<Review> findReviewsByMovieIdWithUser(@Param("movieId") Integer movieId);
}