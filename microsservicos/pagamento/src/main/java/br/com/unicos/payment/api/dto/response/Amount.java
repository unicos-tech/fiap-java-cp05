package br.com.unicos.payment.api.dto.response;

import java.math.BigDecimal;

public class Amount {
    private BigDecimal value; // ex.: 129.90
    private String currency;  // ex.: "BRL"

    public Amount() {}

    public Amount(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
