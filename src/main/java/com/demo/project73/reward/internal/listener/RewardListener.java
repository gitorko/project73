package com.demo.project73.reward.internal.listener;

import com.demo.project73.common.CustomEvent;
import com.demo.project73.common.PrimeReward;
import com.demo.project73.common.SeasonReward;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class RewardListener {
    /**
     * Processes the custom event
     */
    @Async
    @SneakyThrows
    @EventListener
    public void processEvent(CustomEvent myEvent) {
        log.info("Processing CustomEvent {}", myEvent);
        if (myEvent.getEntity() instanceof PrimeReward) {
            log.info("PrimeReward Event: {}", ((PrimeReward) myEvent.getEntity()).getCouponCode());
        }
        if (myEvent.getEntity() instanceof SeasonReward) {
            log.info("SeasonReward Event: {}", ((SeasonReward) myEvent.getEntity()).getCouponCode());
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
