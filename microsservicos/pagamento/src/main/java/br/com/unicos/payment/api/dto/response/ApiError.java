package br.com.unicos.payment.api.dto.response;

import java.util.List;

/**
 * RFC 7807 (Problem Details). Use no @ControllerAdvice.
 */
public class ApiError {
    private String type;    // ex.: "/errors/validation"
    private String title;   // ex.: "Dados inválidos"
    private int status;     // ex.: 422
    private String detail;  // ex.: "Campos obrigatórios ausentes."
    private String traceId; // para suporte/observabilidade
    private List<FieldErrorItem> errors; // opcional: lista de violações de campo

    public ApiError() {}

    public ApiError(String type, String title, int status, String detail, String traceId, List<FieldErrorItem> errors) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.traceId = traceId;
        this.errors = errors;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public String getTraceId() { return traceId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }

    public List<FieldErrorItem> getErrors() { return errors; }
    public void setErrors(List<FieldErrorItem> errors) { this.errors = errors; }
}