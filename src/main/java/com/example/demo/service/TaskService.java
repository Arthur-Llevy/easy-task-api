package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.request.TaskRequestDTO;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks;
    }

    public Task createTask(TaskRequestDTO taskRequestDTO) {
        Task taskToCreate = new Task();
        taskToCreate.setTitle(taskRequestDTO.getTitle());
        taskToCreate.setCompleted(taskRequestDTO.getCompleted());

        return taskRepository.save(taskToCreate);
    }
}
