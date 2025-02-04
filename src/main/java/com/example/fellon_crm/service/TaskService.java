package com.example.fellon_crm.service;

import com.example.fellon_crm.entity.Task;
import com.example.fellon_crm.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    public Task updateTask(Long id, Task taskDetails) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        System.out.println("Updating task with id: " + id);  // Логирование для проверки
        if (taskRepository.existsById(id)) {
            taskDetails.setId(id);
            return taskRepository.save(taskDetails);
        }
        return null;

    }
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public void save(Task task) {
        taskRepository.save(task);
    }
}
