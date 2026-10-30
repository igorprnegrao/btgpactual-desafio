package br.com.negraoipr.btgpactualdesafio.consumer.dto;

import java.util.List;

public record OrderCreatedEvent(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemEvent> itens

) {


}
