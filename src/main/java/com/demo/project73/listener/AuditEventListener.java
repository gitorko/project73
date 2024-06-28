package com.demo.project73.listener;

import com.demo.project73.pojo.Audit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuditEventListener {

    @ApplicationModuleListener
    public void processAuditEvent(Audit audit) {
        log.info("Modulith Audit Event Received: {}", audit.getMessage());
    }

}
