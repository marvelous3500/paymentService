package com.paymentservice.paymentservice.controller;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentservice.paymentservice.paystackIntegration.model.PaystackWebhookRequest;
import com.paymentservice.paymentservice.service.PayStackPaymentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/paystack-webhook")
@AllArgsConstructor
public class PaystackWebhookRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaystackWebhookRestController.class);
    PayStackPaymentService paymentService;

    
    @PostMapping
    public ResponseEntity<?> updateTransactionStatus( @RequestBody PaystackWebhookRequest requestBody,
     HttpServletRequest request) {
      /// validate  payment
      LOGGER.info("@@@@@@@@@@@@@@ am called " + request.toString());
        return ResponseEntity.ok(ResponseEntity.EMPTY);
    }
}

