package com.demo.project73;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Slf4j
@EnableAsync
@Service
public class CustomEventListener {

    /**
     * When the application is ready it triggers this event
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        log.info("Triggered when application ready!");
    }

    /**
     * Processes the custom event
     */
    @Async
    @EventListener
    public void processEvent(CustomEvent myEvent) {
        log.info("Received event {}", myEvent);
        if (myEvent.getEntity() instanceof Project) {
            log.info("Project Name: {}", ((Project) myEvent.getEntity()).getName());
        }
        if (myEvent.getEntity() instanceof Audit) {
            log.info("Audit Message: {}", ((Audit) myEvent.getEntity()).getMessage());
        }
    }

}
