package br.com.unicos.payment.domain.details;

public class PixDetails {
    private String chavePix;   // email/cel/cnpj/aleatória (ideal: mascarar/hashear)
    private String tipoChave;  // EMAIL|CELULAR|CNPJ|ALEATORIA

    public String getChavePix() { return chavePix; }
    public void setChavePix(String chavePix) { this.chavePix = chavePix; }
    public String getTipoChave() { return tipoChave; }
    public void setTipoChave(String tipoChave) { this.tipoChave = tipoChave; }
}
