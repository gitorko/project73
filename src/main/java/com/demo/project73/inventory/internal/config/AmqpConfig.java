package com.demo.project73.inventory.internal.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    public static final String ORDER_QUEUE = "order.queue";
    public static final String EXCHANGE_NAME = "project73.exchange";
    public static final String ROUTING_KEY = "order.queue";
    public static final String EVENT_TARGET = "project73.exchange::order.queue";

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(ORDER_QUEUE).build();
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }
}
