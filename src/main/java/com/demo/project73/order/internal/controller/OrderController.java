package com.demo.project73.order.internal.controller;

import java.time.LocalDate;

import com.demo.project73.order.internal.domain.Order;
import com.demo.project73.order.internal.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    final OrderService orderService;

    @PostMapping("/api/order")
    public Order placeOrder(@RequestBody Order order) {
        order.setOrderDate(LocalDate.now());
        return orderService.placeOrder(order);
    }

}
