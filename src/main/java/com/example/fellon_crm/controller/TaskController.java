package com.example.fellon_crm.controller;

import com.example.fellon_crm.entity.Task;
import com.example.fellon_crm.service.CustomerService;
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
    @Autowired
    private CustomerService customerService;
    // Получение всех задач и отображение их на странице
    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks"; // Страница со списком задач
    }
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "edit-task"; // Страница редактирования задачи
        }
        return "redirect:/tasks"; // Если задача не найдена, перенаправляем на список задач
    }
    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task.getId(), task);
        return "redirect:/tasks"; // После обновления возвращаемся на список задач
    }
    @GetMapping("/create")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("customers", customerService.getAllCustomers()); // Добавляем список клиентов
        return "create_task";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks"; // После удаления возвращаемся на список задач
    }
}
