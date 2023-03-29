package com.paymentservice.paymentservice.paystackIntegration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class paystackTransectionResponse {
    private boolean status;
    private String message;
    private JsonData data;

    @Data
    @Builder(toBuilder = true)
    public static class JsonData {
        private String reference;
        private String authorizationUrl;
        private String accessCode;
    }
    
}