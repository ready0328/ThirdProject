package com.avi6.service;

import com.avi6.domain.Member;
import com.avi6.dto.MemberFormDTO;
import com.avi6.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long register(MemberFormDTO memberFormDTO) {
        if (isEmailTaken(memberFormDTO.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        Member member = Member.builder()
                .email(memberFormDTO.getEmail())
                .username(memberFormDTO.getUsername()) // 사용자 이름은 여전히 저장합니다.
                .password(passwordEncoder.encode(memberFormDTO.getPassword()))
                .build();
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public boolean authenticate(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
        	logger.warn("없는 사용자: {}", email);
            return false;
        }
        boolean passwordMatches = passwordEncoder.matches(password, member.getPassword());
        logger.info("비밀번호 일치 여부: {}", passwordMatches);
        return passwordMatches;
    }

    @Override
    public boolean isEmailTaken(String email) {
        return memberRepository.findByEmail(email) != null;
    }
    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
