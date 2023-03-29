package com.paymentservice.paymentservice.paystackIntegration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaystackTransectionRequest {
    private String email;
    private Double amount;

}
