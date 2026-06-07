package com.example.routing.controller;

import com.example.routing.model.TransactionRequest;
import com.example.routing.model.TransactionResponse;
import com.example.routing.service.RoutingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller slice test using MockMvc to validate HTTP layer behavior,
 * including validation failure handling and success path.
 */
@WebMvcTest(RoutingController.class)
public class RoutingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoutingService routingService;

    @Test
    public void post_process_success() throws Exception {
        TransactionRequest req = new TransactionRequest();
        req.setTransactionId("tx-10");
        req.setSourceSystem("S1");
        req.setDestinationSystem("S2");
        req.setAmount(10.0);
        req.setCurrency("USD");

        when(routingService.processTransaction(any())).thenReturn(new TransactionResponse("tx-10", "STANDARD_GATEWAY", "OK", "Routed"));

        mockMvc.perform(post("/api/v1/transactions/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routedTo").value("STANDARD_GATEWAY"));
    }

    @Test
    public void post_process_validationFailure() throws Exception {
        TransactionRequest req = new TransactionRequest();
        // missing required fields

        mockMvc.perform(post("/api/v1/transactions/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("validation_failed"));
    }
}
