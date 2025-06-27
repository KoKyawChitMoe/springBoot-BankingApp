package com.binary.service;

import com.binary.entity.Customer;
import com.binary.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);

    }

    public Optional<Customer> findById(long custId) {
        return Optional.ofNullable(customerRepository.findById(custId).orElseThrow(() -> new RuntimeException("Customer Id Dose Not Exist")));

    }

    @Transactional
    public Customer deposit(long custId, double amount) {
        if (amount <= 0) throw new IllegalArgumentException(" Deposit amount Must be Positive");

        Customer customer = findById(custId).get();
        customer.setCustAccountBalance(customer.getCustAccountBalance() + amount);
        return customer;
    }

    @Transactional
    public Customer withdraw(long custId, double amount) {
        if (amount <= 0) throw new IllegalArgumentException(" Withdraw amount Must be Positive");

        Customer customer = findById(custId).get();
        if (customer.getCustAccountBalance() < amount) {
            throw new RuntimeException("Insufficient Fund");

        }
        customer.setCustAccountBalance(customer.getCustAccountBalance() - amount);
        return customer;
    }
}
