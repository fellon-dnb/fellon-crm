package com.example.fellon_crm.service;

import com.example.fellon_crm.entity.Customer;
import com.example.fellon_crm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomersById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        if (customer.getStatus() == null) {
            customer.setStatus(Customer.Status.ACTIVE); // Статус по умолчанию
        }
        System.out.println("Creating customer with status: " + customer.getStatus()); // Логирование
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhone(updatedCustomer.getPhone());
            existingCustomer.setStatus(updatedCustomer.getStatus());
            return customerRepository.save(existingCustomer);
        }).orElseThrow(() -> new IllegalArgumentException("Клиент с id " + id + " не найден"));
    }
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

