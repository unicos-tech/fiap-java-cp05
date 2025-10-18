package br.com.unicos.payment.api.dto.request;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PagamentoCoerenteValidator.class)
public @interface PagamentoCoerente {
    String message() default "O tipo de dadosPagamento não corresponde ao metodo de pagamento.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
