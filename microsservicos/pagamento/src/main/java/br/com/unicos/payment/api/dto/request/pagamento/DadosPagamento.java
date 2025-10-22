package br.com.unicos.payment.api.dto.request.pagamento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * O JSON DEVE conter "tipo": "pix" | "cartao" dentro de dadosPagamento.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo",
        visible = true // deixa Jackson ver o campo também nos filhos, se existir
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartaoDados.class, name = "cartao"),
        @JsonSubTypes.Type(value = PixDados.class,   name = "pix")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class DadosPagamento { }