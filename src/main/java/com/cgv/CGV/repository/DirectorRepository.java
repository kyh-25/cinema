package com.cgv.CGV.repository;
import com.cgv.CGV.entity.Director;
import com.cgv.CGV.entity.DirectorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, DirectorId> {

    List<Director> findDirectorsByMovieId(int movieId);
}