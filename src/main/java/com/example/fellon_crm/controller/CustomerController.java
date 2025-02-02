package com.example.fellon_crm.controller;

import com.example.fellon_crm.entity.Customer;
import com.example.fellon_crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }
    @GetMapping("/create")
    public String showCreateCustomerForm() {
        return "create_customer";
    }
    @PostMapping("/create")
    public String createCustomer(@ModelAttribute Customer customer) {
        // Убедитесь, что статус передан и корректно установлен
        if (customer.getStatus() == null) {
            customer.setStatus(Customer.Status.ACTIVE);  // Устанавливаем статус по умолчанию, если не передан
        }
        customerService.createCustomer(customer);
        return "redirect:/customers";
    }
    @GetMapping("/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Optional<Customer> customer = customerService.getCustomersById(id);
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "edit_customer";
        }
        return "redirect:/customers";
    }
    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        if (customerService.updateCustomer(id, customer) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Клиент не найден");
        }
        return "redirect:/customers";
    }
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
    @GetMapping("/api")
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
