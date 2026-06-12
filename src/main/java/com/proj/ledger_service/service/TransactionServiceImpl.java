package com.proj.ledger_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.proj.ledger_service.entity.Transaction;
import com.proj.ledger_service.kafka.TransactionProducer;
import com.proj.ledger_service.model.TransactionStatus;
import com.proj.ledger_service.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionProducer transactionProducer;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        // Implementation for creating a transaction
        transaction.setStatus(TransactionStatus.PENDING);
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Stream the saved transaction entity straight to Apache Kafka
        transactionProducer.sendTransactionEvent(savedTransaction);

        return savedTransaction;
    }

    @Override
    public Transaction getTransactionById(UUID id) {
        // Implementation for retrieving a transaction by ID
        return transactionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Transaction not found with id: "+ id));
    }

    @Override
    public Transaction updateTransaction(UUID id, Transaction updatedTransaction) {
        Transaction existingTransaction = transactionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Transaction not found with id: "+ id));

        existingTransaction.setVendorName(updatedTransaction.getVendorName());
        existingTransaction.setAmount(updatedTransaction.getAmount());
        existingTransaction.setCurrency(updatedTransaction.getCurrency());
        existingTransaction.setRawInvoiceText(updatedTransaction.getRawInvoiceText());

        if(updatedTransaction.getChartOfAccountCategory() != null) {
            existingTransaction.setChartOfAccountCategory(updatedTransaction.getChartOfAccountCategory());
        }

        return transactionRepository.save(existingTransaction);
    }

    @Override
    public void deleteTransaction(UUID id) {
        if(!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found with id: "+id);
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions;
    }

}
