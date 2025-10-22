package br.com.unicos.payment.messaging;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PaymentEvent {
    private String phoneNumber;
    private String message;
    private String eventType;   // PAYMENT_APPROVED | PAYMENT_FAILED
    private String paymentId;   // UUID gerado no controller/service
    private String orderId;     // mapeado do idExterno
    private BigDecimal amount;
    private String currency;    // "BRL"
    private String status;      // APPROVED | FAILED
    private Instant occurredAt; // UTC
    private String traceId;     // UUID

    public static PaymentEvent approved(String paymentId, String orderId,String phoneNumber,
                                        BigDecimal amount, String currency, String traceId) {
        PaymentEvent e = new PaymentEvent();
        e.phoneNumber = phoneNumber;
        e.message = "pagamento do pedido: " + orderId + " foi aprovado!";
        e.eventType  = "PAYMENT_APPROVED";
        e.paymentId  = paymentId;
        e.orderId    = orderId;
        e.amount     = amount;
        e.currency   = currency;
        e.status     = "APPROVED";
        e.occurredAt = Instant.now();
        e.traceId    = (traceId == null || traceId.isBlank()) ? UUID.randomUUID().toString() : traceId;
        return e;
    }

    public static PaymentEvent failed(String paymentId, String orderId, String phoneNumber,
                                      BigDecimal amount, String currency, String traceId) {
        PaymentEvent e = new PaymentEvent();
        e.phoneNumber = phoneNumber;
        e.message = "pagamento do pedido: " + orderId + " foi recusado!";
        e.eventType  = "PAYMENT_FAILED";
        e.paymentId  = paymentId;
        e.orderId    = orderId;
        e.amount     = amount;
        e.currency   = currency;
        e.status     = "FAILED";
        e.occurredAt = Instant.now();
        e.traceId    = (traceId == null || traceId.isBlank()) ? UUID.randomUUID().toString() : traceId;
        return e;
    }

    public String getPhoneNumber() {return phoneNumber;}
    public String getMessage() {return message;}
    public String getEventType() { return eventType; }
    public String getPaymentId() { return paymentId; }
    public String getOrderId() { return orderId; }
    public BigDecimal getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getStatus() { return status; }
    public Instant getOccurredAt() { return occurredAt; }
    public String getTraceId() { return traceId; }
}
