package com.avi6.service;

import com.avi6.domain.Member;
import com.avi6.dto.MemberFormDTO;

public interface MemberService {
    Long register(MemberFormDTO memberFormDTO);// 등록
    boolean authenticate(String email, String password); // 로그인
    boolean isEmailTaken(String email); // 이메일 중복 확인
    Member findByEmail(String email);
    
}
