package br.com.unicos.payment.service;

import br.com.unicos.payment.api.dto.request.PaymentRequestV1;
import br.com.unicos.payment.api.dto.response.PaymentResponse;
import br.com.unicos.payment.api.mapper.PaymentApiMapper;
import br.com.unicos.payment.messaging.PaymentEvent;
import br.com.unicos.payment.messaging.PaymentEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentEventPublisher publisher;
    private final PaymentApiMapper mapper;

    public PaymentService(PaymentEventPublisher publisher, PaymentApiMapper mapper) {
        this.publisher = publisher;
        this.mapper = mapper;
    }

    public PaymentResponseWithHeaders process(PaymentRequestV1 req, String correlationId, String idempotencyKey) {
        String traceId = (correlationId == null || correlationId.isBlank())
                ? UUID.randomUUID().toString() : correlationId;
        String paymentId = UUID.randomUUID().toString();

        boolean approved = req.getValor() != null && req.getValor().compareTo(BigDecimal.ZERO) > 0;

        PaymentEvent evt = approved
                ? mapper.toApprovedEvent(req, paymentId, traceId)
                : mapper.toFailedEvent(req, paymentId, traceId);
        publisher.publish(evt);

        PaymentResponse body = mapper.toAcceptedResponse(req, paymentId, traceId);
        return new PaymentResponseWithHeaders(body, traceId, idempotencyKey);
    }

    public static class PaymentResponseWithHeaders {
        public final PaymentResponse body; public final String traceId; public final String idempotencyKey;
        public PaymentResponseWithHeaders(PaymentResponse body, String traceId, String idempotencyKey) {
            this.body = body; this.traceId = traceId; this.idempotencyKey = idempotencyKey;
        }
    }
}
