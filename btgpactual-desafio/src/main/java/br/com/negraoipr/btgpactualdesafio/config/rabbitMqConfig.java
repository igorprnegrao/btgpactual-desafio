package br.com.negraoipr.btgpactualdesafio.config;

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitMqConfig {
    public static  final String ORDER_CREATED_QUEUE = "btg-pactual-order-created";

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
