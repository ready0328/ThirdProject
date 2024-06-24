package com.avi6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avi6.domain.Todo;
import com.avi6.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo, @RequestParam String email) {
        Todo createdTodo = todoService.saveTodoForUser(todo, email);
        return ResponseEntity.ok(createdTodo);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam String email) {
        List<Todo> todos = todoService.getTodosForUser(email);
        return ResponseEntity.ok(todos);
    }
}

