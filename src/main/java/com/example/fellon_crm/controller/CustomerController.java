package com.example.fellon_crm.controller;

import com.example.fellon_crm.entity.Customer;
import com.example.fellon_crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Controller  // Аннотация для веб-контроллера
@RequestMapping("/customers")  // Основной путь для всех операций с клиентами
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Для работы с веб-страницей (например, для отображения списка клиентов)
    @GetMapping
    public String getAllCustomers(Model model) {
        // Получаем всех клиентов и добавляем их в модель
        model.addAttribute("customers", customerService.getAllCustomers());
        // Возвращаем имя шаблона (например, customers.html), который будет рендерить список
        return "customers";
    }

    // Для отображения формы для добавления нового клиента
    @GetMapping("/create")
    public String showCreateCustomerForm() {
        // Возвращаем имя шаблона для создания нового клиента (например, create_customer.html)
        return "create_customer";
    }

    // Для создания нового клиента (обрабатываем POST запрос)
    @PostMapping("/create")
    public String createCustomer(@ModelAttribute Customer customer) {
        // Сохраняем нового клиента через сервис
        customerService.createCustomer(customer);
        // Перенаправляем на страницу с клиентами после добавления
        return "redirect:/customers";
    }

    // Для отображения формы редактирования клиента
    @GetMapping("/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Optional<Customer> customer = customerService.getCustomersById(id);
        if (customer.isPresent()) {
            // Добавляем найденного клиента в модель
            model.addAttribute("customer", customer.get());
            // Возвращаем шаблон для редактирования клиента (например, edit_customer.html)
            return "edit_customer";
        }
        // Если клиента не нашли, перенаправляем на страницу с клиентами
        return "redirect:/customers";
    }

    // Для обновления данных клиента
    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        // Обновляем данные клиента через сервис
        customerService.updateCustomer(id, customer);
        // Перенаправляем на страницу с клиентами после обновления
        return "redirect:/customers";
    }

    // Для удаления клиента
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        // Удаляем клиента через сервис
        customerService.deleteCustomer(id);
        // Перенаправляем на страницу с клиентами после удаления
        return "redirect:/customers";
    }

    // Методы для работы с API

    @GetMapping("/api")  // Пример отдельного пути для API
    public ResponseEntity<List<Customer>> getAllCustomersApi() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Customer> getCustomerByIdApi(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomersById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/api")
    public ResponseEntity<Customer> createCustomerApi(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<Customer> updateCustomerApi(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<Void> deleteCustomerApi(@PathVariable Long id) {
        return customerService.deleteCustomer(id) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
