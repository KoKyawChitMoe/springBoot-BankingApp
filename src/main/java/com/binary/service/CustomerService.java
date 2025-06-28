package com.binary.service;

import com.binary.entity.Customer;
import com.binary.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findById(long custId) {
        return customerRepository.findById(custId)
                .orElseThrow(() -> new RuntimeException("Customer Id Does Not Exist"));
    }

    @Transactional
    public Customer deposit(long custId, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        Customer customer = findById(custId);
        customer.setCustAccountBalance(customer.getCustAccountBalance() + amount);
        return customer;
    }

    @Transactional
    public Customer withdraw(long custId, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdraw amount must be positive");
        Customer customer = findById(custId);
        if (customer.getCustAccountBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        customer.setCustAccountBalance(customer.getCustAccountBalance() - amount);
        return customer;
    }
}

