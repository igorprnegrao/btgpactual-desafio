package br.com.negraoipr.btgpactualdesafio.repositories;

import br.com.negraoipr.btgpactualdesafio.controllers.OrderResponse;
import br.com.negraoipr.btgpactualdesafio.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends MongoRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
