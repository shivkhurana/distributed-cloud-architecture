package com.example.routing.model;

/**
 * DTO representing the response after a transaction has been routed.
 */
public class TransactionResponse {

    private String transactionId;
    private String routedTo;
    private String status;
    private String message;

    public TransactionResponse() {
    }

    public TransactionResponse(String transactionId, String routedTo, String status, String message) {
        this.transactionId = transactionId;
        this.routedTo = routedTo;
        this.status = status;
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRoutedTo() {
        return routedTo;
    }

    public void setRoutedTo(String routedTo) {
        this.routedTo = routedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
