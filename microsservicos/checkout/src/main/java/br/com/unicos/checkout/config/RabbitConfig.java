package br.com.unicos.checkout.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "orders.exchange";
    public static final String ROUTING  = "order.created";
    public static final String QUEUE    = "stock.update.queue";

    @Bean TopicExchange orders(){ return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build(); }
    @Bean Queue stock(){ return QueueBuilder.durable(QUEUE).build(); }
    @Bean Binding binding(){ return BindingBuilder.bind(stock()).to(orders()).with(ROUTING); }
    @Bean MessageConverter json(){ return new Jackson2JsonMessageConverter(); }
}
