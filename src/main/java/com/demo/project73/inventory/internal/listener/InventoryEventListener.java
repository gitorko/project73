package com.demo.project73.inventory.internal.listener;

import com.demo.project73.common.OrderEvent;
import com.demo.project73.common.InventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InventoryEventListener {

    final ApplicationEventPublisher applicationEventPublisher;

    @ApplicationModuleListener
    public void processOrderEvent(OrderEvent orderEvent) {
        log.info("[Inventory] Order Event Received: {}", orderEvent);
        applicationEventPublisher.publishEvent(InventoryEvent.builder().message("inventory updated").build());
    }
}
