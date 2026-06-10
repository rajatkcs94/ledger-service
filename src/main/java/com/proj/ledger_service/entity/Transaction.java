package com.proj.ledger_service.entity;

import java.util.UUID;

import com.proj.ledger_service.model.TransactionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    private String vendorName;

    private Double amount;

    private String currency;

    private String chartOfAccountCategory;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    
    private String rawInvoiceText;
}
