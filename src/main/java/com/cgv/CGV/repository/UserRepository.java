package com.cgv.CGV.repository;
import com.cgv.CGV.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserId(String userId);
    boolean existsByUserId(String userId);
}

