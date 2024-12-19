package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.entities.Customer;
import com.fullstack.shop.web.routine.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<Customer> getAlCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer searchById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer saveOrUpdate(Customer customer) {
        return customerRepository.save(customer);
    }

    public Boolean delete(Integer id) {
        boolean checkResult = customerRepository.existsById(id);
        if (checkResult) {
            customerRepository.deleteById(id);
        }
        return checkResult;
    }
}
