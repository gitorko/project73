package com.demo.project73.controller;

import com.demo.project73.pojo.Customer;
import com.demo.project73.service.AsyncService;
import com.demo.project73.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    final AsyncService asyncService;
    final CustomerService customerService;

    @PostMapping("/api/customer")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/api/custom-event")
    public void publishCustomEvent() {
        asyncService.publishCustomEvent();
    }

    @PutMapping("/api/object-event")
    public void publishObjectEvent() {
        asyncService.publishObjectEvent();
    }
}
