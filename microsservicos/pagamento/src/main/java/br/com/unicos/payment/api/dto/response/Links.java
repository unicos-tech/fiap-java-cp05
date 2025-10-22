package br.com.unicos.payment.api.dto.response;

public class Links {
    private String self;
    private String status;

    public Links() {}

    public Links(String self, String status) {
        this.self = self; this.status = status;
    }

    public String getSelf() { return self; }
    public void setSelf(String self) { this.self = self; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
