package br.com.negraoipr.btgpactualdesafio.services;

import br.com.negraoipr.btgpactualdesafio.consumer.dto.OrderCreatedEvent;
import br.com.negraoipr.btgpactualdesafio.controllers.OrderResponse;
import br.com.negraoipr.btgpactualdesafio.entity.OrderEntity;
import br.com.negraoipr.btgpactualdesafio.entity.OrderItems;
import br.com.negraoipr.btgpactualdesafio.repositories.OrderRespository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRespository orderRespository;

    public void save(OrderCreatedEvent event){

        var entity = new OrderEntity();
        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));


        orderRespository.save(entity);
    }

    private static BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest){
        var orders =  orderRespository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }

    private static List<OrderItems> getOrderItems(OrderCreatedEvent event) {
        return event.itens().stream()
                .map(i -> new OrderItems(i.produto(), i.quantidade(), i.preco()))
                .toList();
    }
}
