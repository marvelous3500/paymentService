package com.paymentservice.paymentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentservice.paymentservice.paystackIntegration.model.PaystackTransectionRequest;
import com.paymentservice.paymentservice.service.PayStackPaymentService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public final class PaymentController {

    private PayStackPaymentService payStackPaymentService;

    @PostMapping
    public ResponseEntity<?> makePayment( @RequestBody PaystackTransectionRequest request){
        return ResponseEntity.ok(payStackPaymentService.makePayment(request));
    }
    
}
