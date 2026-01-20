package com.example.todoapp.dto;

import java.time.Instant;
import java.time.LocalDate;

public record TodoResponse(
        Long id,
        String title,
        String description,
        boolean completed,
        LocalDate dueDate,
        Instant createdAt,
        Instant updatedAt
) {
}
