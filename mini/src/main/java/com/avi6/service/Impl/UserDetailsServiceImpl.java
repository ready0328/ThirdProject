package com.avi6.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.avi6.domain.Member;
import com.avi6.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        logger.info("로그인 시도: {}", email);
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            logger.warn("없는 사용자임: {}", member + "!!!");
            throw new UsernameNotFoundException("없는 사용자임: " + email);
        }
        logger.info("사용자 로드 성공: {}", email);
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles("USER") // 사용자의 권한(role)을 설정해야 합니다.
                .build();
    }
}
