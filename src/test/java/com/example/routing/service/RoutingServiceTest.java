package com.example.routing.service;

import com.example.routing.exception.ApiException;
import com.example.routing.model.TransactionRequest;
import com.example.routing.model.TransactionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link RoutingService} demonstrating Agile-style test cases.
 */
public class RoutingServiceTest {

    private RoutingService routingService;

    @BeforeEach
    public void setup() {
        routingService = new RoutingService();
    }

    @Test
    public void process_highValue_goesToPriority() {
        TransactionRequest req = new TransactionRequest();
        req.setTransactionId("tx-1");
        req.setSourceSystem("SYS_A");
        req.setDestinationSystem("SYS_B");
        req.setAmount(200000.0);
        req.setCurrency("USD");

        TransactionResponse resp = routingService.processTransaction(req);
        Assertions.assertEquals("PRIORITY_GATEWAY", resp.getRoutedTo());
    }

    @Test
    public void process_vipDestination_goesToVip() {
        TransactionRequest req = new TransactionRequest();
        req.setTransactionId("tx-2");
        req.setSourceSystem("SYS_A");
        req.setDestinationSystem("VIP_ACCOUNT");
        req.setAmount(10.0);
        req.setCurrency("USD");

        TransactionResponse resp = routingService.processTransaction(req);
        Assertions.assertEquals("VIP_GATEWAY", resp.getRoutedTo());
    }

    @Test
    public void process_invalidAmount_throwsApiException() {
        TransactionRequest req = new TransactionRequest();
        req.setTransactionId("tx-3");
        req.setSourceSystem("SYS_A");
        req.setDestinationSystem("SYS_B");
        req.setAmount(-5.0);
        req.setCurrency("USD");

        Assertions.assertThrows(ApiException.class, () -> routingService.processTransaction(req));
    }
}
