package com.example.fellon_crm.controller;

import com.example.fellon_crm.entity.Task;
import com.example.fellon_crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Получение всех задач и отображение их на странице
    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task-list"; // Страница со списком задач
    }

    // Получение задачи по id и отображение формы редактирования
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "edit-task"; // Страница редактирования задачи
        }
        return "redirect:/tasks"; // Если задача не найдена, перенаправляем на список задач
    }

    // Обновление задачи
    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task.getId(), task);
        return "redirect:/tasks"; // После обновления возвращаемся на список задач
    }

    // Удаление задачи
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks"; // После удаления возвращаемся на список задач
    }
}
