package br.com.negraoipr.btgpactualdesafio.consumer.dto;

import java.math.BigDecimal;

public record OrderItemEvent(
        String produto,
        Integer quantidade,
        BigDecimal preco
) {
}
