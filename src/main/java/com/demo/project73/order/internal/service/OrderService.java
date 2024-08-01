package com.demo.project73.order.internal.service;

import java.util.UUID;

import com.demo.project73.common.CustomEvent;
import com.demo.project73.common.PrimeReward;
import com.demo.project73.common.SeasonReward;
import com.demo.project73.order.internal.domain.Order;
import com.demo.project73.common.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    final ApplicationEventPublisher applicationEventPublisher;

    /**
     *  @ApplicationModuleListener need a transactional boundary else won't run.
     */
    @Transactional
    public Order placeOrder(Order order) {
        for (String item : order.getItems()) {
            OrderEvent orderEvent = OrderEvent.builder()
                    .orderId(order.getOrderId())
                    .item(item)
                    .orderDate(order.getOrderDate())
                    .build();
            log.info("Publishing Order: {}", orderEvent);
            applicationEventPublisher.publishEvent(orderEvent);
        }

        PrimeReward coupon1 = PrimeReward.builder()
                .id(UUID.randomUUID())
                .couponCode("coupon-code-" + UUID.randomUUID())
                .build();
        SeasonReward coupon2 = SeasonReward.builder()
                .id(UUID.randomUUID())
                .couponCode("coupon-code-" + UUID.randomUUID())
                .build();
        CustomEvent<PrimeReward> customEvent1 = new CustomEvent(this, coupon1);
        CustomEvent<SeasonReward> customEvent2 = new CustomEvent(this, coupon2);
        log.info("Publishing CustomEvent: {}", customEvent1);
        applicationEventPublisher.publishEvent(customEvent1);
        log.info("Publishing CustomEvent: {}", customEvent2);
        applicationEventPublisher.publishEvent(customEvent2);
        return order;
    }
}
