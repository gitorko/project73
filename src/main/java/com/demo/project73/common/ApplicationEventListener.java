package com.demo.project73.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationEventListener {
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
}
