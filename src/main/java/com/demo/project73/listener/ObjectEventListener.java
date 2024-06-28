package com.demo.project73.listener;

import com.demo.project73.pojo.Coupon;
import com.demo.project73.pojo.Customer;
import com.demo.project73.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableAsync
public class ObjectEventListener {

    /**
     * Create a new audit event after processing and posts it back to the queue
     */
    @EventListener
    @Async
    public void processCustomer(Customer customer) {
        log.info("Customer Event Received: {}", customer);
    }

    @EventListener
    @Async
    public void processCoupon(Coupon coupon) {
        log.info("Coupon Event Received: {}", coupon);
    }

    @EventListener
    @Async
    public void processOrder(Order order) {
        log.info("Order Event Received: {}", order.getName());
    }
}
