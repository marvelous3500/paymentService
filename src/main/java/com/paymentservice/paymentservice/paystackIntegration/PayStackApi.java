package com.paymentservice.paymentservice.paystackIntegration;

import com.paymentservice.paymentservice.paystackIntegration.model.PaystackTransectionRequest;
import com.paymentservice.paymentservice.paystackIntegration.model.PaystackVerifyResponse;
import com.paymentservice.paymentservice.paystackIntegration.model.paystackTransectionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PayStackApi {
    String AUTHORIZATION_HEADER = "Authorization";

    @GET("/transaction/verify/{transactionRef}")
    Call<PaystackVerifyResponse> verifyPayment(
        @Header(AUTHORIZATION_HEADER) String authToken,
        @Path("transactionRef") String transectionRef
    );

    @POST("/transaction/initialize")
    Call<paystackTransectionResponse> makePayment(
        @Header(AUTHORIZATION_HEADER) String authToken,
        @Body PaystackTransectionRequest request
    );



}
