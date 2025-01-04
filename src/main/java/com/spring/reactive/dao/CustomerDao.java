package com.spring.reactive.dao;

import com.spring.reactive.model.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private void sleep(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(this::sleep)
                .mapToObj(val -> new Customer(val, "Customer no. " + val))
                .peek(System.out::println)
                .toList();
    }

    public Flux<Customer> getCustomerFlux() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .map(val -> new Customer(val, "Customer no. " + val))
                .doOnNext(System.out::println);
    }
}
