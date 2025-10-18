package br.com.unicos.payment.api.dto.request.response;

import java.time.Instant;

public class PaymentResponse {
    private String paymentId;
    private String status;
    private String method;
    private Instant receivedAt;

}
