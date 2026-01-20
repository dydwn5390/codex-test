package com.example.todoapp.service;

import com.example.todoapp.dto.TodoRequest;
import com.example.todoapp.dto.TodoResponse;
import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponse> findAll() {
        return todoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public TodoResponse findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "할일을 찾을 수 없습니다."));
        return toResponse(todo);
    }

    public TodoResponse create(TodoRequest request) {
        Todo todo = new Todo(
                request.title(),
                request.description(),
                Optional.ofNullable(request.completed()).orElse(false),
                request.dueDate()
        );
        return toResponse(todoRepository.save(todo));
    }

    public TodoResponse update(Long id, TodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "할일을 찾을 수 없습니다."));
        todo.setTitle(request.title());
        todo.setDescription(request.description());
        todo.setCompleted(Optional.ofNullable(request.completed()).orElse(false));
        todo.setDueDate(request.dueDate());
        return toResponse(todoRepository.save(todo));
    }

    public TodoResponse toggle(Long id, boolean completed) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "할일을 찾을 수 없습니다."));
        todo.setCompleted(completed);
        return toResponse(todoRepository.save(todo));
    }

    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "할일을 찾을 수 없습니다.");
        }
        todoRepository.deleteById(id);
    }

    private TodoResponse toResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted(),
                todo.getDueDate(),
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}
