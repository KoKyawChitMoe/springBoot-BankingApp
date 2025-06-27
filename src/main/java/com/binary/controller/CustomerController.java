package com.binary.controller;


import com.binary.entity.Customer;
import com.binary.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.save(customer));
    }


    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable long custId) {
        return ResponseEntity.ok(customerService.findById(custId));

    }

    @PatchMapping("/deposit/{custId}/{amount}")
    public ResponseEntity<Customer> deposit(@PathVariable long custId, @PathVariable double amount) {
        return ResponseEntity.ok(customerService.deposit(custId, amount));

    }

    @PatchMapping("/withdraw/{custId}/{amount}")
    public ResponseEntity<Customer> withdraw(@PathVariable long custId, @PathVariable double amount) {
        return ResponseEntity.ok(customerService.withdraw(custId, amount));

    }

}
