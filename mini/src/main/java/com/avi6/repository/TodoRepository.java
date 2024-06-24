package com.avi6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avi6.domain.Member;
import com.avi6.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(Member user);
}
