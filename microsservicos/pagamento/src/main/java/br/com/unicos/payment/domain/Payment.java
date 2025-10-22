package br.com.unicos.payment.domain;

import br.com.unicos.payment.domain.details.CardDetails;
import br.com.unicos.payment.domain.details.PixDetails;

import java.math.BigDecimal;
import java.time.Instant;

public class Payment {

    private String id;              // gerado no fluxo (UUID.toString())
    private String idExterno;       // id de negócio (idempotência “lógica” se/quando precisar)
    private BigDecimal valor;
    private String moeda;           // "BRL"
    private String metodo;          // "PIX" | "CARTAO"
    private PaymentStatus status;   // RECEBIDO | APROVADO | ...

    private Instant receivedAt;
    private String traceId;

    // opcional: ecoar o header se você quiser propagar
    private String idempotencyKey;

    // detalhes específicos (apenas um dos dois será preenchido)
    private CardDetails card;
    private PixDetails pix;

    // ===== getters/setters =====
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getIdExterno() { return idExterno; }
    public void setIdExterno(String idExterno) { this.idExterno = idExterno; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getMoeda() { return moeda; }
    public void setMoeda(String moeda) { this.moeda = moeda; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public Instant getReceivedAt() { return receivedAt; }
    public void setReceivedAt(Instant receivedAt) { this.receivedAt = receivedAt; }
    public String getTraceId() { return traceId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }
    public CardDetails getCard() { return card; }
    public void setCard(CardDetails card) { this.card = card; }
    public PixDetails getPix() { return pix; }
    public void setPix(PixDetails pix) { this.pix = pix; }
}