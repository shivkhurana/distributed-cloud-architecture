package com.example.routing.service;

import com.example.routing.exception.ApiException;
import com.example.routing.model.Transaction;
import com.example.routing.model.TransactionRequest;
import com.example.routing.model.TransactionResponse;
import com.example.routing.repository.InMemoryTransactionRepository;
import com.example.routing.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

/**
 * Core business service implementing routing logic.
 *
 * The service is intentionally simple but follows strict OO principles:
 * - Single Responsibility: routing decisions separated from persistence.
 * - Encapsulation: internal mapping decisions are private.
 * - Testability: dependencies are injected and can be mocked.
 */
@Service
public class RoutingService {

    private final TransactionRepository repository;

    public RoutingService() {
        // Use in-memory repository for scaffolding and simulation.
        this.repository = new InMemoryTransactionRepository();
    }

    /**
     * Process a transaction request and return routing result.
     * This method validates business constraints and throws {@link ApiException}
     * for deterministic client-facing errors.
     *
     * @param req incoming transaction request
     * @return transaction response with routing metadata
     */
    public TransactionResponse processTransaction(TransactionRequest req) {
        // Defensive null checks (defensive programming for "zero anomalies").
        if (req == null) {
            throw new ApiException("invalid_request", "Request payload is required");
        }
        if (Objects.isNull(req.getAmount()) || req.getAmount() <= 0) {
            throw new ApiException("invalid_amount", "Amount must be a positive number");
        }

        // Simulate routing decision: a simple rule engine
        String routedTo = decideRoute(req);

        // Build transaction entity and persist
        Transaction tx = new Transaction();
        tx.setTransactionId(req.getTransactionId());
        tx.setSourceSystem(req.getSourceSystem());
        tx.setDestinationSystem(req.getDestinationSystem());
        tx.setAmount(req.getAmount());
        tx.setCurrency(req.getCurrency());
        tx.setRoutedTo(routedTo);
        tx.setProcessedAt(Instant.now());

        repository.save(tx);

        return new TransactionResponse(req.getTransactionId(), routedTo, "OK", "Routed successfully");
    }

    /**
     * Look up a previously routed transaction by id.
     *
     * @param transactionId id to lookup
     * @return response if found
     */
    public TransactionResponse lookup(String transactionId) {
        return repository.findById(transactionId)
                .map(tx -> new TransactionResponse(tx.getTransactionId(), tx.getRoutedTo(), "OK", "Found"))
                .orElseThrow(() -> new ApiException("not_found", "Transaction not found"));
    }

    /**
     * Routing decision engine (kept private to the service to encapsulate rules).
     *
     * Network routing protocols and enterprise rules can be simulated here.
     * Current algorithm: route high-value transactions to "PRIORITY_GATEWAY";
     * otherwise route to a standard gateway based on destination system name.
     *
     * @param req input request
     * @return routing destination identifier
     */
    private String decideRoute(TransactionRequest req) {
        if (req.getAmount() != null && req.getAmount() >= 100000.0) {
            return "PRIORITY_GATEWAY";
        }
        // Use destination system normalized as fallback
        String dest = req.getDestinationSystem();
        if (dest == null || dest.isBlank()) {
            throw new ApiException("invalid_destination", "Destination system is required");
        }
        // Simulate network prefix-based routing (e.g., VIP -> VIP_GATEWAY)
        if (dest.toUpperCase().startsWith("VIP")) {
            return "VIP_GATEWAY";
        }
        return "STANDARD_GATEWAY";
    }
}
