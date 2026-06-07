package com.example.routing.controller;

import com.example.routing.model.TransactionRequest;
import com.example.routing.model.TransactionResponse;
import com.example.routing.service.RoutingService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing routing endpoints.
 * Follows strict separation of concerns: controller orchestrates HTTP concerns,
 * delegates business logic to the service layer and returns DTOs for clients.
 */
@RestController
@RequestMapping(path = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoutingController {

    private final RoutingService routingService;

    public RoutingController(RoutingService routingService) {
        this.routingService = routingService;
    }

    /**
     * Process a transaction. Business errors map to well-defined API errors.
     *
     * @param request transaction payload validated by Jakarta Validation
     * @return routing response
     */
    @PostMapping(path = "/process", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> process(@Valid @RequestBody TransactionRequest request) {
        TransactionResponse resp = routingService.processTransaction(request);
        return ResponseEntity.ok(resp);
    }

    /**
     * Retrieve a previously processed transaction by id.
     *
     * @param id transaction id
     * @return transaction response
     */
    @GetMapping(path = "{id}")
    public ResponseEntity<TransactionResponse> get(@PathVariable("id") String id) {
        TransactionResponse resp = routingService.lookup(id);
        return ResponseEntity.ok(resp);
    }
}
