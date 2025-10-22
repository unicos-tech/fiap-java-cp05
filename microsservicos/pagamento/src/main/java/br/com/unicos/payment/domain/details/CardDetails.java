package br.com.unicos.payment.domain.details;

public class CardDetails {
    // Guarde apenas token/últimos 4; nunca PAN completo
    private String tokenCartao;
    private String validade;   // MM/AA (apenas para demo; em produção evite)
    private String bandeira;
    private String nomeTitular;

    public String getTokenCartao() { return tokenCartao; }
    public void setTokenCartao(String tokenCartao) { this.tokenCartao = tokenCartao; }
    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }
    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }
    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
}
