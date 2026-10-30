package br.com.negraoipr.btgpactualdesafio.consumer;

import br.com.negraoipr.btgpactualdesafio.consumer.dto.OrderCreatedEvent;
import br.com.negraoipr.btgpactualdesafio.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.negraoipr.btgpactualdesafio.config.rabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
@RequiredArgsConstructor
public class OrderCreateConsumer {

    private final Logger logger = LoggerFactory.getLogger(OrderCreateConsumer.class);

    private final OrderService orderService;

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message ){
        logger.info("Message consumed: {}", message);

        orderService.save(message.getPayload());
    }


    @Bean
    public Declarable orderCreatedQueue(){
        return new Queue(ORDER_CREATED_QUEUE);
    }


}
