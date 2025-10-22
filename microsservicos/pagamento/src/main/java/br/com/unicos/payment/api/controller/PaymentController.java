package br.com.unicos.payment.api.controller;

import br.com.unicos.payment.api.dto.request.PaymentRequestV1;
import br.com.unicos.payment.api.dto.response.PaymentResponse;
import br.com.unicos.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/v1/payments", produces = "application/json")
public class PaymentController {

    private final PaymentService service;
    public PaymentController(PaymentService service) { this.service = service; }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<PaymentResponse> create(
            @Valid @RequestBody PaymentRequestV1 body,
            @RequestHeader(name = "X-Correlation-Id", required = false) String correlationId,
            @RequestHeader(name = "Idempotency-Key", required = false) String idempotencyKey) {

        var out = service.process(body, correlationId, idempotencyKey);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Correlation-Id", out.traceId);
        if (out.idempotencyKey != null && !out.idempotencyKey.isBlank()) {
            headers.add("Idempotency-Key", out.idempotencyKey);
        }

        URI location = URI.create(out.body.getLinks().getSelf());
        return ResponseEntity.accepted().headers(headers).location(location).body(out.body);
    }

    @GetMapping("/{paymentId}") // <= estava truncado
    public ResponseEntity<PaymentResponse> get(@PathVariable String paymentId) {
        return ResponseEntity.notFound().build(); // sem persistência por enquanto
    }
}
