package br.com.unicos.payment.api.dto.request.pagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CartaoDados extends DadosPagamento {

    @NotBlank
    private String tokenCartao; // ou "ultimos4" se preferir

    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$") // MM/AA
    private String validade;

    private String bandeira;
    private String nomeTitular;

    public CartaoDados() {}

    public String getTokenCartao() { return tokenCartao; }
    public void setTokenCartao(String tokenCartao) { this.tokenCartao = tokenCartao; }

    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }

    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }

    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
}
