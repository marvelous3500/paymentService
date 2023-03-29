package com.paymentservice.paymentservice.paystackIntegration;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String message) {
        super(message);
    }
    
}
