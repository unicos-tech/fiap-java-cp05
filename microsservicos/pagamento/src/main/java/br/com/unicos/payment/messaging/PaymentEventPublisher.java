package br.com.unicos.payment.messaging;

import br.com.unicos.payment.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventPublisher {
    private static final Logger log = LoggerFactory.getLogger(PaymentEventPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    public PaymentEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(PaymentEvent evt) {
        String rk = RabbitConfig.ROUTING;

        log.info("Publishing event={} rk={} paymentId={} traceId={}",
                evt.getEventType(), rk, evt.getPaymentId(), evt.getTraceId());

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, rk, evt);
    }
}
