package br.com.unicos.payment.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "payment.exchange";
    public static final String ROUTING  = "payment.changed";
    public static final String QUEUE    = "sms.queue";

    @Bean TopicExchange payments(){ return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build(); }
    @Bean Queue sms(){ return QueueBuilder.durable(QUEUE).build(); }
    @Bean Binding binding(){ return BindingBuilder.bind(sms()).to(payments()).with(ROUTING); }
    @Bean MessageConverter json(){ return new Jackson2JsonMessageConverter(); }
}
