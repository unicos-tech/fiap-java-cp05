package br.com.unicos.sms_service.messaging;

import br.com.unicos.sms_service.dto.SmsRequest;
import br.com.unicos.sms_service.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final SmsService service;

    @RabbitListener(queues = "sms.queue")
    public void onMessage(SmsRequest order) {
        service.apply(order);
    }
}

