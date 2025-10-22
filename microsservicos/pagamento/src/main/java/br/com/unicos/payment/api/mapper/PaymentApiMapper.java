package br.com.unicos.payment.api.mapper;

import br.com.unicos.payment.api.dto.request.PaymentRequestV1;
import br.com.unicos.payment.api.dto.response.*;
import br.com.unicos.payment.messaging.PaymentEvent;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class PaymentApiMapper {
    public PaymentResponse toAcceptedResponse(PaymentRequestV1 req, String paymentId, String traceId) {
        PaymentResponse r = new PaymentResponse();
        r.setPaymentId(paymentId);
        r.setStatus("RECEBIDO");
        r.setMethod(req.getMetodo().name());
        r.setAmount(new Amount(req.getValor(), req.getMoeda()));
        r.setReceivedAt(Instant.now());
        r.setTraceId(traceId);
        r.setLinks(new Links("/v1/payments/" + paymentId, "/v1/payments/" + paymentId));
        return r;
    }
    public PaymentEvent toApprovedEvent(PaymentRequestV1 req, String paymentId, String traceId) {
        return PaymentEvent.approved(paymentId, req.getIdExterno(),req.getPhoneNumber(), req.getValor(), req.getMoeda(), traceId);
    }
    public PaymentEvent toFailedEvent(PaymentRequestV1 req, String paymentId, String traceId) {
        return PaymentEvent.failed(paymentId, req.getIdExterno(), req.getPhoneNumber() ,req.getValor() ,req.getMoeda(), traceId);
    }
}
