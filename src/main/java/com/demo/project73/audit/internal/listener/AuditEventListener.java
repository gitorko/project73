package com.demo.project73.audit.internal.listener;

import com.demo.project73.common.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditEventListener {

    @ApplicationModuleListener
    public void processOrderEvent(OrderEvent orderEvent) {
        log.info("[Audit] Order Event Received: {}", orderEvent);
    }

}
