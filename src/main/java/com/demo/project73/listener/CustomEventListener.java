package com.demo.project73.listener;

import com.demo.project73.pojo.Coupon;
import com.demo.project73.pojo.CustomEvent;
import com.demo.project73.pojo.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
public class CustomEventListener {

    /**
     * Processes the custom event
     */
    @SneakyThrows
    @EventListener
    public void processEvent(CustomEvent myEvent) {
        log.info("Processing CustomEvent {}", myEvent);
        if (myEvent.getEntity() instanceof Order) {
            log.info("Order Event: {}", ((Order) myEvent.getEntity()).getName());
        }
        if (myEvent.getEntity() instanceof Coupon) {
            log.info("Coupon Event: {}", ((Coupon) myEvent.getEntity()).getCouponCode());
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

}
