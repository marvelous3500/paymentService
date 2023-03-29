package com.paymentservice.paymentservice.paystackIntegration;
import java.io.IOException;

import io.vavr.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paymentservice.paymentservice.paystackIntegration.model.PaymentStatusType;
import com.paymentservice.paymentservice.paystackIntegration.model.PaystackTransectionRequest;
import com.paymentservice.paymentservice.paystackIntegration.model.PaystackVerifyResponse;
import com.paymentservice.paymentservice.paystackIntegration.model.paystackTransectionResponse;
import com.paymentservice.paymentservice.util.ServiceClientBulder;

import  lombok.AllArgsConstructor;
import retrofit2.Response;

@AllArgsConstructor
public class PaystackExternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaystackExternalService.class);
    private static final String BEARER = "Bearer ";
    private static final String PAYSTACK_TX_STATUS_SUCCESS = "success";
    private static final String PAYSTACK_TX_STATUS_FAILURE = "failure";
    private final PayStackApi paystackApi;

    public PaystackExternalService(String baseUrl) {
        this.paystackApi = ServiceClientBulder.buildClient(baseUrl, PayStackApi.class);
    }

    public PaymentStatusType verifyPayment(String AuthTooken, String transectionRef){
        PaymentStatusType status  = PaymentStatusType.PENDING ;

        Response<PaystackVerifyResponse>  response = Try.of( () ->  paystackApi.verifyPayment(AuthTooken,transectionRef)
        .execute()

        ).getOrElseThrow((e) ->{ 
            LOGGER.info(e.getMessage());
            throw new ServiceUnavailableException("Paystack is currently unable to verify payment ");
         });

         if (isTransactionSuccessful(transectionRef, response)){
            status = PaymentStatusType.SUCCESSFUL;
         }

         if(isTransactionFailed(transectionRef, response)){
            status = PaymentStatusType.FAILED;
         }
         return status;
    }

    public paystackTransectionResponse makePayment( String payStackKey, PaystackTransectionRequest request){
        Response<paystackTransectionResponse> response = Try.of(
            () -> paystackApi.makePayment(BEARER + payStackKey, request)
            .execute()
        )
        .getOrElseThrow((e) -> {
            LOGGER.info(e.getMessage());
            throw new ServiceUnavailableException("Paystack is currently unavailable to make payment");
        });
        
        validateResponse(response);

        return response.body();
    }

    private boolean isTransactionSuccessful(String txRef, Response<PaystackVerifyResponse> response) {
        return response.body().getData().getStatus().equals(PAYSTACK_TX_STATUS_SUCCESS)
            && response.body().getData().getReference().equals(txRef);
    }
     
     private boolean isTransactionFailed(String txRef, Response<PaystackVerifyResponse> response) {
        return response.body().getData().getStatus().equals(PAYSTACK_TX_STATUS_FAILURE)
            && response.body().getData().getReference().equals(txRef);
    }
   
    private void validateResponse(Response response) {
        if (!response.isSuccessful()) {
            String message = null;
            try {
                message = response.errorBody().string();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
            throw new PaystackException(message);
        }
    }
  
    
}
