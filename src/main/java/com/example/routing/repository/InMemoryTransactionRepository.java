package com.example.routing.repository;

import com.example.routing.model.Transaction;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Thread-safe in-memory implementation of {@link TransactionRepository}.
 * Uses a ConcurrentHashMap to simulate persistent storage for testing and
 * high-volume simulations.
 */
public class InMemoryTransactionRepository implements TransactionRepository {

    private final ConcurrentMap<String, Transaction> store = new ConcurrentHashMap<>();

    @Override
    public void save(Transaction tx) {
        store.put(tx.getTransactionId(), tx);
    }

    @Override
    public Optional<Transaction> findById(String transactionId) {
        return Optional.ofNullable(store.get(transactionId));
    }
}
