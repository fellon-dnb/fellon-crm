package com.example.fellon_crm.controller;

import com.example.fellon_crm.entity.Task;
import com.example.fellon_crm.service.CustomerService;
import com.example.fellon_crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "edit_task";
        }
        return "redirect:/tasks";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        if (updatedTask != null) {
            return "redirect:/tasks";
        }
        return "error";
    }

    @GetMapping("/create")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "create_task";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
