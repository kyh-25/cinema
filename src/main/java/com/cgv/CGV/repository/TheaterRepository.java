package com.cgv.CGV.repository;
import com.cgv.CGV.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {}
