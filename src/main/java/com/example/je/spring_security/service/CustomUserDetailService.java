package com.example.je.spring_security.service;

import com.example.je.spring_security.domain.User;
import com.example.je.spring_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // JPA를 사용해서 사용자 정보를 데이터베이스에서 조회하고
    // 그 결과를 UserDetails 객체에 저장하여 반환

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        log.info(" CustomUserDetailService - loadUserByUsername 호출 !! ");

        // JPA와 MariaDB를 이용해서 사용자 정보를 확인
        User user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을수 없습니다!!"));

        // 인증에 성공하면 userdetails 객체 생성하고 반환
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserid())
                .password(user.getPasswd())
                .roles(user.getRole())
                .build();
    }

}
