package com.demo.project73.common;


import static com.demo.project73.inventory.internal.config.AmqpConfig.EVENT_TARGET;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.modulith.events.Externalized;

@Externalized(target = EVENT_TARGET)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {
    private String message;
}
