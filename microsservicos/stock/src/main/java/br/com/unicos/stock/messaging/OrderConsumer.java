package br.com.unicos.stock.messaging;

import br.com.unicos.stock.dto.OrderRequest;
import br.com.unicos.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final StockService service;

    @RabbitListener(queues = "stock.update.queue")
    public void onMessage(OrderRequest order) {
        service.apply(order);
    }
}

