package com.cgv.CGV.service;

import com.cgv.CGV.entity.User;
import com.cgv.CGV.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public boolean register(User user) {
        if (userRepository.existsByUserId(user.getUserId())) {
            return false; // 중복 아이디
        }
        user.setBalance(0);
        user.setPoint(0);
        user.setGrade("bronze");
        userRepository.save(user);
        return true;
    }

    // 로그인 인증
    public User login(String userId, String password) {
        User user = userRepository.findByUserId(userId);

        if (user.getUserId() == null) {
            return null;
        }

        if (!user.getPw().equals(password)) {
            return null;
        }

        return user;
    }
}