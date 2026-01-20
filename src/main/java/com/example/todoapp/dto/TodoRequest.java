package com.example.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record TodoRequest(
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 100, message = "제목은 100자 이하여야 합니다.")
        String title,
        @Size(max = 1000, message = "설명은 1000자 이하여야 합니다.")
        String description,
        Boolean completed,
        LocalDate dueDate
) {
}
