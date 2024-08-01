package com.demo.project73.notification.internal.listener;

import com.demo.project73.common.InventoryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {

    public static final String EXCHANGE_NAME = "project73.exchange";
    public static final String ORDER_QUEUE = "order.queue";

    @RabbitListener(queues = ORDER_QUEUE)
    public void receive(InventoryEvent event) {
        log.info("[Notification] Inventory Event Received '{}'", event.getMessage());
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).build();
    }

}
