package com.cgv.CGV.repository;

import com.cgv.CGV.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}

