package br.com.unicos.payment.api.dto.response;

/** Item de erro de campo para validações de entrada. */
public class FieldErrorItem {
    private String field;
    private String message;

    public FieldErrorItem() {}
    public FieldErrorItem(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
