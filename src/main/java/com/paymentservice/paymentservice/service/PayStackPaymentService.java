package com.paymentservice.paymentservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.paymentservice.paymentservice.paystackIntegration.PaystackExternalService;
import com.paymentservice.paymentservice.paystackIntegration.model.PaymentStatusType;
import com.paymentservice.paymentservice.paystackIntegration.model.PaystackTransectionRequest;
import com.paymentservice.paymentservice.paystackIntegration.model.paystackTransectionResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PayStackPaymentService {

    private PaystackExternalService paymentService;

    @Value("${paystack_key}")
    private transient String key; 

    public paystackTransectionResponse makePayment(PaystackTransectionRequest request){
        return paymentService.makePayment(key, request);
    }

    public PaymentStatusType verifyPayment(String transectionRef){
        return paymentService.verifyPayment(key, transectionRef);
    }



    
}
