package com.cgv.CGV.repository;

import com.cgv.CGV.entity.Moviegenre;
import com.cgv.CGV.entity.MoviegenreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviegenreRepository extends JpaRepository<Moviegenre, MoviegenreId> {

    List<Moviegenre> findGenresByMovieId(int movieId);
}