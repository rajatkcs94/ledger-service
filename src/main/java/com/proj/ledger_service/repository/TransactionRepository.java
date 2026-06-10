package com.proj.ledger_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.ledger_service.entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
