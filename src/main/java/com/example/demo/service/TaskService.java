package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.request.TaskRequestDTO;
import com.example.demo.DTO.request.TaskUpdateRequestDTO;
import com.example.demo.exception.TaskInternalServerErrorException;
import com.example.demo.exception.TaskMissingRequestBodyException;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        try {
            List<Task> tasks = taskRepository.findAll();

            return tasks;
        } catch (Exception ex) {
            throw new TaskInternalServerErrorException("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Task> getById(int id) {
        try {
            Optional<Task> task = taskRepository.findById(id);

            if (task.isEmpty()) {
                throw new TaskNotFoundException("Task not found with this id", HttpStatus.NOT_FOUND);
            }

            return task;
        } catch (TaskNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TaskInternalServerErrorException("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public Task createTask(TaskRequestDTO taskRequestDTO) {
        try {
            Task taskToCreate = new Task();

            if (taskRequestDTO.getTitle().isEmpty()) {
                throw new TaskMissingRequestBodyException("The filed title is required", HttpStatus.BAD_REQUEST);
            }

            taskToCreate.setTitle(taskRequestDTO.getTitle());
            taskToCreate.setCompleted(taskRequestDTO.getCompleted());

            return taskRepository.save(taskToCreate);
        } catch (TaskMissingRequestBodyException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TaskInternalServerErrorException("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Task updateTask(TaskUpdateRequestDTO taskUpdateRequestDTO, int id) {
        try {
            Optional<Task> taskToUpdate = taskRepository.findById(id);
        
            if (taskToUpdate.isEmpty()) {
                throw new TaskNotFoundException("Task not foud with this id", HttpStatus.NOT_FOUND);
            }

            taskToUpdate.get().setTitle(taskUpdateRequestDTO.getTitle().isEmpty() ? taskToUpdate.get().getTitle() : taskUpdateRequestDTO.getTitle());
            taskToUpdate.get().setCompleted(taskUpdateRequestDTO.getCompleted() != null ? taskUpdateRequestDTO.getCompleted() : taskToUpdate.get().getCompleted());

            return taskRepository.save(taskToUpdate.get());
        } catch (TaskNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TaskInternalServerErrorException("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
      
    }

    public void delteById(int id) {
        try {
            Optional<Task> task = taskRepository.findById(id);

            if (task.isEmpty()) {
                throw new TaskNotFoundException("Task not foud with this id", HttpStatus.NOT_FOUND);
            }

            taskRepository.delete(task.get());
        } catch (TaskNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TaskInternalServerErrorException("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
