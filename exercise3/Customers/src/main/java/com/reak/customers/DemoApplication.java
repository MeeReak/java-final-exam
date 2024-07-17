package com.reak.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class DemoApplication {

    private List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    @GetMapping("/customers/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();
            customerToUpdate.setName(updatedCustomer.getName());
            customerToUpdate.setEmail(updatedCustomer.getEmail());
            return customerToUpdate;
        } else {
            return null;
        }
    }
}

class Customer {
    private Long id;
    private String name;
    private String email;

    public Customer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}