package com.example.demo.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {
    @NotNull
    private String title;

    @NotNull
    private Boolean completed = false;
}
