package com.github.scotchg.sample.controller;

import com.github.scotchg.sample.Repository.TodoRepository;
import com.github.scotchg.sample.entity.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping("/todos")
    @ResponseBody
    public List<TodoEntity> get(){
        return todoRepository.findAll();
    }

    @GetMapping("/todos/{id}")
    @ResponseBody
    public TodoEntity getOne(@PathVariable String id){
        return todoRepository.findOneById(id).orElseThrow(IllegalArgumentException::new);
    }

    @PostMapping("/todos/{id}")
    @ResponseBody
    @Transactional
    public TodoEntity post(@PathVariable String id, @RequestParam String description) {
        return todoRepository.persistAndFlush(TodoEntity.builder().id(id).description(description).status("CREATED").build());
    }

    @PutMapping("/todos/{id}")
    @ResponseBody
    @Transactional
    public TodoEntity put(@PathVariable String id,
                          @RequestParam Optional<String> description,
                          @RequestParam String status) {
        TodoEntity entity = todoRepository.findOneById(id).orElseThrow(IllegalArgumentException::new);
        description.ifPresent(entity::setDescription);
        entity.setStatus(status);
        return todoRepository.saveAndFlush(entity);
    }
}
