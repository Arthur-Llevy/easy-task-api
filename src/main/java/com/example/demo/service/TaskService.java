package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.request.TaskRequestDTO;
import com.example.demo.DTO.request.TaskUpdateRequestDTO;
import com.example.demo.exception.TaskNotFoundException;
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

    public Optional<Task> getById(int id) {
        Optional<Task> task = taskRepository.findById(id);

        return task;
    }

    public Task createTask(TaskRequestDTO taskRequestDTO) {
        Task taskToCreate = new Task();
        taskToCreate.setTitle(taskRequestDTO.getTitle());
        taskToCreate.setCompleted(taskRequestDTO.getCompleted());

        return taskRepository.save(taskToCreate);
    }

    public Task updateTask(TaskUpdateRequestDTO taskUpdateRequestDTO, int id) {
        Optional<Task> taskToUpdate = taskRepository.findById(id);
        
        if (taskToUpdate.isEmpty()) {
            throw new TaskNotFoundException("Task not foud with this id");
        }

        taskToUpdate.get().setTitle(taskUpdateRequestDTO.getTitle().isEmpty() ? taskToUpdate.get().getTitle() : taskUpdateRequestDTO.getTitle());
        taskToUpdate.get().setCompleted(taskUpdateRequestDTO.getCompleted() != null ? taskUpdateRequestDTO.getCompleted() : taskToUpdate.get().getCompleted());

        return taskRepository.save(taskToUpdate.get());
    }

    public void delteById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        taskRepository.delete(task.get());
    }
}
