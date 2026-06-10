package com.proj.ledger_service.service;

import java.util.List;
import java.util.UUID;

import com.proj.ledger_service.entity.Transaction;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Transaction getTransactionById(UUID id);
    List<Transaction> getAllTransactions();
    Transaction updateTransaction(UUID id, Transaction updateTransaction);
    void deleteTransaction(UUID id);
}
