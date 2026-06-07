package com.example.routing.repository;

import com.example.routing.model.Transaction;

import java.util.Optional;

/**
 * Repository abstraction for storing routed transactions.
 * Implementations may be in-memory for this scaffold to simplify testing.
 */
public interface TransactionRepository {

    /**
     * Persist a transaction.
     *
     * @param tx the transaction to store
     */
    void save(Transaction tx);

    /**
     * Retrieve a transaction by id.
     *
     * @param transactionId id to lookup
     * @return optional transaction
     */
    Optional<Transaction> findById(String transactionId);
}
