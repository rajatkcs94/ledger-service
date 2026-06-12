package com.proj.ledger_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.proj.ledger_service.entity.Transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionProducer {
    private static final String TOPIC = "financial-transactions";
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public void sendTransactionEvent(Transaction transaction) {
        log.info("### -> Producing message to kafka -> {}", transaction.getId());
        this.kafkaTemplate.send(TOPIC, transaction.getId().toString(), transaction);
    }
    
}
