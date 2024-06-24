package com.avi6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avi6.domain.Member;
import com.avi6.domain.Todo;
import com.avi6.repository.MemberRepository;
import com.avi6.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Todo saveTodoForUser(Todo todo, String email) {
        Member user = memberRepository.findByEmail(email);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodosForUser(String email) {
        Member user = memberRepository.findByEmail(email);
        return todoRepository.findByUser(user);
    }
}