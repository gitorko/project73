package com.demo.project73.service;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import com.demo.project73.pojo.Audit;
import com.demo.project73.pojo.CustomEvent;
import com.demo.project73.pojo.Project;
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
public class CustomAsync {

    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * Runs the job in async thread
     */
    @Async
    public void doSomeAsyncTask1() {
        log.info("Running task!");
        IntStream.range(0, 1).forEach(i -> {
            Project project = new Project(i, "Project_" + i);
            Audit audit = new Audit("Audit Message " + i, LocalDateTime.now());
            CustomEvent<Project> customEvent1 = new CustomEvent(this, project);
            CustomEvent<Audit> customEvent2 = new CustomEvent(this, audit);
            log.info("Publishing CustomEvent: {}", customEvent1);
            applicationEventPublisher.publishEvent(customEvent1);
            log.info("Publishing CustomEvent: {}", customEvent2);
            applicationEventPublisher.publishEvent(customEvent2);
            applicationEventPublisher.publishEvent(project);
        });
    }

}
