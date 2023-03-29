package com.paymentservice.paymentservice.paystackIntegration.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaystackWebhookRequest  implements Serializable{
    private String event;
    private JsonData data;

    @Data
    @Builder(toBuilder  = true)
    public  static class JsonData implements Serializable {
        private Long id;
        private String domain;
        private String status;
        private String reference;
        private BigDecimal amount;
        private Date paidAt;
        private Date createdAt;
        private String channel;
        private String currency;
    }
    
}
