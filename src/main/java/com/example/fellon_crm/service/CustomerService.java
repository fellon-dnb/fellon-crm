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
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Long id, Customer customerDetails) {
        if (customerRepository.existsById(id)) {
            customerDetails.setId(id);
            return customerRepository.save(customerDetails);
        }
        return null;
    }
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

