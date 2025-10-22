package br.com.unicos.payment.api.dto.request;

import br.com.unicos.payment.api.dto.request.pagamento.DadosPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@PagamentoCoerente
public class PaymentRequestV1 {

    private String phoneNumber;

    @NotBlank private String idExterno;

    @NotNull @Positive private BigDecimal valor;

    @NotBlank @Size(min=3,max=3) private String moeda; // "BRL"

    @NotNull private PaymentMethod metodo;

    @NotNull @Valid private DadosPagamento dadosPagamento;

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
    public String getPhoneNumber() {
        return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}