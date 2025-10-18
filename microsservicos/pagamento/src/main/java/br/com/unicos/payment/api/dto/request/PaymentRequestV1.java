package br.com.unicos.payment.api.dto.request;

import br.com.unicos.payment.api.dto.request.pagamento.DadosPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@PagamentoCoerente
public class PaymentRequestV1 {

    @NotBlank
    private String idExterno;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotBlank
    private String moeda;

    @NotNull
    private PaymentMethod metodo;

    @NotNull
    @Valid
    private DadosPagamento dadosPagamento;

    public PaymentRequestV1() {}

    public String getIdExterno() { return idExterno; }
    public void setIdExterno(String idExterno) { this.idExterno = idExterno; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getMoeda() { return moeda; }
    public void setMoeda(String moeda) { this.moeda = moeda; }

    public PaymentMethod getMetodo() { return metodo; }
    public void setMetodo(PaymentMethod metodo) { this.metodo = metodo; }

    public DadosPagamento getDadosPagamento() { return dadosPagamento; }
    public void setDadosPagamento(DadosPagamento dadosPagamento) { this.dadosPagamento = dadosPagamento; }
}
