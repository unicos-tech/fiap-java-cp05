package br.com.unicos.payment.api.dto.request.pagamento;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Tipo base polimórfico para Jackson. O campo "tipo" no JSON decide o subtipo.
 * Ex.: "tipo": "cartao" | "pix"
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartaoDados.class, name = "cartao"),
        @JsonSubTypes.Type(value = PixDados.class,   name = "pix")
})
public abstract class DadosPagamento {
    // sem campos; apenas a âncora para o polimorfismo
}
