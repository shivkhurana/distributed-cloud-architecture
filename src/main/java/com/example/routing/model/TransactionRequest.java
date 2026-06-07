package com.example.routing.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * DTO representing a transaction request payload.
 * Validation annotations enforce strict rules to simulate "zero data anomalies".
 */
public class TransactionRequest {

    @NotBlank(message = "transactionId is required")
    private String transactionId;

    @NotBlank(message = "sourceSystem is required")
    private String sourceSystem;

    @NotBlank(message = "destinationSystem is required")
    private String destinationSystem;

    @NotNull(message = "amount is required")
    @Positive(message = "amount must be positive")
    private Double amount;

    @Pattern(regexp = "^[A-Z]{3}$", message = "currency must be a 3-letter ISO code")
    private String currency = "USD";

    public TransactionRequest() {
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
}
