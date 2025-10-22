package br.com.unicos.payment.api.dto.request.pagamento;

import jakarta.validation.constraints.NotBlank;

public class PixDados extends DadosPagamento {
    @NotBlank private String chavePix;
    private String tipoChave;

    public String getChavePix() { return chavePix; }
    public void setChavePix(String chavePix) { this.chavePix = chavePix; }
    public String getTipoChave() { return tipoChave; }
    public void setTipoChave(String tipoChave) { this.tipoChave = tipoChave; }
}