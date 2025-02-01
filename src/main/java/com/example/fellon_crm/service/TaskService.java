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

    // Получение всех задач
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Получение задачи по id
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Создание новой задачи
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Обновление существующей задачи
    public Task updateTask(Long id, Task taskDetails) {
        if (taskRepository.existsById(id)) {
            taskDetails.setId(id);
            return taskRepository.save(taskDetails);
        }
        return null; // Задача не найдена
    }

    // Удаление задачи
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false; // Задача не найдена
    }
}
