package com.demo.project73;

import org.springframework.context.ApplicationEvent;

public class CustomEvent<T> extends ApplicationEvent {

    private T entity;

    public CustomEvent(Object source, T entity) {
        super(source);
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }
}
