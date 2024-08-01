package com.demo.project73.order.internal.listener;

import com.demo.project73.common.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventListener {

    @ApplicationModuleListener
    public void processOrder(OrderEvent orderEvent) {
        log.info("[Order] Order Event Received: {}", orderEvent);
    }

}
