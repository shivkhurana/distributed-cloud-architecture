package com.example.routing.model;

import java.time.Instant;

/**
 * Simple entity representing a routed transaction stored in-memory.
 */
public class Transaction {

    private String transactionId;
    private String sourceSystem;
    private String destinationSystem;
    private Double amount;
    private String currency;
    private String routedTo;
    private Instant processedAt;

    public Transaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDestinationSystem() {
        return destinationSystem;
    }

    public void setDestinationSystem(String destinationSystem) {
        this.destinationSystem = destinationSystem;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRoutedTo() {
        return routedTo;
    }

    public void setRoutedTo(String routedTo) {
        this.routedTo = routedTo;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Instant processedAt) {
        this.processedAt = processedAt;
    }
}
