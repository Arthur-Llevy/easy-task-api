package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.request.TaskRequestDTO;
import com.example.demo.DTO.request.TaskUpdateRequestDTO;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    // get - /api/taks
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable int id) {
        Optional<Task> task = taskService.getById(id);

        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> createNewTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        Task createdTask = taskService.createTask(taskRequestDTO);

        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable int id, @RequestBody TaskUpdateRequestDTO taskUpdateRequestDTO) {
        try {
            Task updatedTask = taskService.updateTask(taskUpdateRequestDTO, id);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTask);

        } catch (TaskNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable int id) {
        taskService.delteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
