package com.example.je.spring_security.repository;

import com.example.je.spring_security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserid(String username);
    // 회원 가입 시 이메일, 아이디 중복확인
    boolean existsByUserid(String userid);
    boolean existsByEmail(String email);

}
