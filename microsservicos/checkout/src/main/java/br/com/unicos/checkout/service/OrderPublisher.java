package br.com.unicos.checkout.service;

import br.com.unicos.checkout.config.RabbitConfig;
import br.com.unicos.checkout.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class OrderPublisher {
    @Autowired
    private RabbitTemplate template;

    public void publish(OrderRequest order){
        template.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING, order);
    }
}