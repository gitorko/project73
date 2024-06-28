package com.demo.project73.service;

import java.util.List;
import java.util.stream.LongStream;

import com.demo.project73.pojo.Coupon;
import com.demo.project73.pojo.CustomEvent;
import com.demo.project73.pojo.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@Slf4j
@RequiredArgsConstructor
public class AsyncService {

    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * Runs the job in async thread
     */
    @Async
    public void publishCustomEvent() {
        log.info("Running doSomeAsyncTask1!");
        LongStream.range(1, 2).forEach(i -> {
            Order order = Order.builder()
                    .id(i)
                    .name("order_" + i)
                    .items(List.of("Oranges", "Chocolate"))
                    .build();
            Coupon coupon = Coupon.builder()
                    .id(i)
                    .couponCode("coupon-code-" + i)
                    .build();
            CustomEvent<Order> customEvent1 = new CustomEvent(this, order);
            CustomEvent<Coupon> customEvent2 = new CustomEvent(this, coupon);
            log.info("Publishing CustomEvent: {}", customEvent1);
            applicationEventPublisher.publishEvent(customEvent1);
            log.info("Publishing CustomEvent: {}", customEvent2);
            applicationEventPublisher.publishEvent(customEvent2);
        });
    }

    @Async
    public void publishObjectEvent() {
        log.info("Running doSomeAsyncTask2!");
        LongStream.range(1, 2).forEach(i -> {
            Order order = Order.builder()
                    .id(i)
                    .name("order_" + i)
                    .items(List.of("Oranges", "Chocolate"))
                    .build();
            Coupon coupon = Coupon.builder()
                    .id(i)
                    .couponCode("coupon-code-" + i)
                    .build();
            log.info("Publishing Order: {}", order);
            applicationEventPublisher.publishEvent(order);
            log.info("Publishing Coupon: {}", coupon);
            applicationEventPublisher.publishEvent(coupon);
        });
    }

}
