package br.com.unicos.payment.api.dto.response;

import java.time.Instant;

public class PaymentResponse {
    private String paymentId;
    private String status;      // RECEBIDO | EM_PROCESSAMENTO | AUTORIZADO | FALHOU
    private String method;      // CARTAO | PIX
    private Amount amount;      // << importa do mesmo pacote
    private Instant receivedAt;
    private String traceId;
    private Links links;        // opcional

    public PaymentResponse() {}

    // getters/setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public Amount getAmount() { return amount; }
    public void setAmount(Amount amount) { this.amount = amount; }

    public Instant getReceivedAt() { return receivedAt; }
    public void setReceivedAt(Instant receivedAt) { this.receivedAt = receivedAt; }

    public String getTraceId() { return traceId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }

    public Links getLinks() { return links; }
    public void setLinks(Links links) { this.links = links; }
}