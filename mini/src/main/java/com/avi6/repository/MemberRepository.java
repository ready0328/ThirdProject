package com.avi6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avi6.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	 Member findByEmail(String email);
	
}
