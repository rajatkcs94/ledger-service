package com.proj.ledger_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.proj.ledger_service.entity.Transaction;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionConsumer {

    @KafkaListener(topics = "financial-transactions", groupId = "ledger-group")
    public void consumeTransactionEvent(Transaction transaction) {
        log.info("### -> Consumed message from Kafka -> ID: {}, Vendor: {}, Amount: {}",
            transaction.getId(),
            transaction.getVendorName(),
            transaction.getAmount(),
            transaction.getCurrency()
        );
    }
}
