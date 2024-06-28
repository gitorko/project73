package com.demo.project73.service;

import com.demo.project73.pojo.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    final ApplicationEventPublisher applicationEventPublisher;

    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer to DB: {}", customer);
        //Save to DB
        log.info("Publishing Customer Event for downstream processing!");
        applicationEventPublisher.publishEvent(customer);
        return customer;
    }
}
