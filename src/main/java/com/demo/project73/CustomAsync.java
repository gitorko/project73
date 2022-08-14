package com.demo.project73;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
        IntStream.range(0, 5).forEach(i -> {
            Project project = new Project(i, "Project_" + i);
            Audit audit = new Audit("Audit Message " + i, LocalDateTime.now());
            log.info("Trigger event: {}", project);
            CustomEvent<Project> projectEvent = new CustomEvent(this, project);
            CustomEvent<Audit> auditEvent = new CustomEvent(this, audit);
            applicationEventPublisher.publishEvent(projectEvent);
            sleep(1);
            applicationEventPublisher.publishEvent(auditEvent);
        });
    }

    @SneakyThrows
    public void sleep(int seconds) {
        TimeUnit.SECONDS.sleep(seconds);
    }

}
