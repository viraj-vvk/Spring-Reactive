package com.spring.reactive.service;

import com.spring.reactive.dao.CustomerDao;
import com.spring.reactive.model.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        System.out.printf("Took: %d millis", System.currentTimeMillis() - start);
        return customers;
    }

    public Flux<Customer> geCustomerFlux() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomerFlux();
        System.out.printf("Took: %d millis", System.currentTimeMillis() - start);
        return customers;
    }
}
