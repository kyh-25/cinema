package com.cgv.CGV.repository;

import com.cgv.CGV.entity.Store;
import com.cgv.CGV.entity.StoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, StoreId> {

    @Query("SELECT s FROM Store s JOIN FETCH s.food WHERE s.theater.id = :theaterId")
    List<Store> findByTheaterId(@Param("theaterId") int theaterId);
}
