package br.com.unicos.payment.api.dto.request;

import br.com.unicos.payment.api.dto.request.pagamento.CartaoDados;
import br.com.unicos.payment.api.dto.request.pagamento.PixDados;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PagamentoCoerenteValidator
        implements ConstraintValidator<PagamentoCoerente, PaymentRequestV1> {

    @Override
    public boolean isValid(PaymentRequestV1 req, ConstraintValidatorContext ctx) {
        if (req == null) return true;
        if (req.getMetodo() == null || req.getDadosPagamento() == null) return true;

        boolean ok =
                (req.getMetodo() == PaymentMethod.CARTAO && req.getDadosPagamento() instanceof CartaoDados)
                        || (req.getMetodo() == PaymentMethod.PIX    && req.getDadosPagamento() instanceof PixDados);

        if (!ok) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(
                            "dadosPagamento não compatível com metodo (" + req.getMetodo() + ")")
                    .addPropertyNode("dadosPagamento")
                    .addConstraintViolation();
        }
        return ok;
    }
}
