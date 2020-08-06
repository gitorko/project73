package com.demo.project73;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class Application implements ApplicationRunner {

    @Autowired
    MyEventListener myEventListener;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        myEventListener.doSomeAsyncTask1();
    }
}

@AllArgsConstructor
@Data
class MyEvent{
    String name;
}

@Slf4j
@EnableAsync
@Service
class MyEventListener {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        log.info("Triggered when application ready!");
    }

    @Async
    @EventListener
    public void doSomeAsyncTask2(MyEvent myEvent) {
        log.info("Received event {}", myEvent);
    }

    @Async
    public void doSomeAsyncTask1() {
        log.info("Running task!");
        IntStream.range(0,5).forEach(i -> {
            sleep(1);
            log.info("Trigger event: {}", i);
            applicationEventPublisher.publishEvent(new MyEvent("EventId: " + i));
        });
    }

    @SneakyThrows
    public void sleep(int seconds) {
        TimeUnit.SECONDS.sleep(1);
    }
}
