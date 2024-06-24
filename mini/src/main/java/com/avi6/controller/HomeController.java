package com.avi6.controller;


import com.avi6.config.JwtTokenProvider;
import com.avi6.domain.Member;
import com.avi6.dto.MemberFormDTO;
import com.avi6.service.MemberService;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final MemberService memberService;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider; // JwtTokenProvider 주입

    @PostMapping("/members")
    public ResponseEntity<?> createMember(@RequestBody MemberFormDTO memberFormDTO) {
        try {
            Long memberId = memberService.register(memberFormDTO);
            return ResponseEntity.ok(memberId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("회원가입 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberFormDTO memberFormDTO) {
        boolean isAuthenticated = memberService.authenticate(memberFormDTO.getEmail(), memberFormDTO.getPassword());

        if (isAuthenticated) {
            Member member = memberService.findByEmail(memberFormDTO.getEmail());
            if (member != null) {
                String token = jwtTokenProvider.generateToken(member.getEmail()); // JWT 토큰 생성
                String username = member.getUsername(); // 여기서 username을 가져옵니다
                Map<String, Object> response = new HashMap<>();
                System.out.println("Username: " + username); // 확인을 위해 로그 출력
                response.put("username", username); // username을 response에 추가
                response.put("token", token);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }
}
