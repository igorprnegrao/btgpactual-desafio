package br.com.negraoipr.btgpactualdesafio.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitMqConfig {
    public static  final String ORDER_CREATED_QUEUE = "btg-pactual-order-created";
}
