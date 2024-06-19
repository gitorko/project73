package com.demo.project73.listener;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import com.demo.project73.pojo.Audit;
import com.demo.project73.pojo.CustomEvent;
import com.demo.project73.pojo.Project;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@EnableAsync
@Service
public class CustomEventListener {

    /**
     * ApplicationStartingEvent -  fired at the start of a run but before any processing
     * ApplicationEnvironmentPreparedEvent - fired when the Environment to be used in the context is available
     * ApplicationContextInitializedEvent- fired when the ApplicationContext is ready
     * ApplicationPreparedEvent - fired when ApplicationContext is prepared but not refreshed
     * ContextRefreshedEvent - fired when an ApplicationContext is refreshed
     * WebServerInitializedEvent - fired after the web server is ready
     * ApplicationStartedEvent - fired after the context has been refreshed but before any application and command-line runners have been called
     * ApplicationReadyEvent - fired to indicate that the application is ready to service
     * ApplicationFailedEvent - fired if there is an exception and the application fails to start
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        log.info("Triggered when application ready!");
    }

    /**
     * Processes the custom event
     */
    @SneakyThrows
    @Async
    @EventListener
    public void processEvent(CustomEvent myEvent) {
        log.info("Processing CustomEvent {}", myEvent);
        if (myEvent.getEntity() instanceof Project) {
            log.info("Project Event: {}", ((Project) myEvent.getEntity()).getName());
        }
        if (myEvent.getEntity() instanceof Audit) {
            log.info("Audit Event: {}", ((Audit) myEvent.getEntity()).getMessage());
        }
    }

    /**
     *  AFTER_COMMIT: The event will be handled when the transaction gets committed successfully.
     *  AFTER_COMPLETION: The event will be handled when the transaction commits or is rolled back.
     *  AFTER_ROLLBACK: The event will be handled after the transaction has rolled back.
     *  BEFORE_COMMIT: The event will be handled before the transaction commit.
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void afterAuditEventProcessed(CustomEvent myEvent) {
        log.info("After CustomEvent processed: {}", myEvent);
    }

    @EventListener
    public void processCustomer(Audit audit) {
        log.info("Audit Specific Event: {}", audit.getMessage());
    }

    /**
     * Create a new event and posts it back to the queue
     */
    @EventListener
    public Audit processEmployee(Project project) {
        log.info("Project Specific Event: {}", project.getName());
        return new Audit("New Event Generated for " + project.getName(), LocalDateTime.now());
    }

}
