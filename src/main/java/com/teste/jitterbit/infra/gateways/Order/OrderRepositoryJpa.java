package com.teste.jitterbit.infra.gateways.Order;

import com.teste.jitterbit.application.gateways.RepositoryOrder;
import com.teste.jitterbit.domain.entitties.Order.Order;
import com.teste.jitterbit.infra.persistence.order.OrderEntity;
import com.teste.jitterbit.infra.persistence.order.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRepositoryJpa implements RepositoryOrder {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper mapper;

    public OrderRepositoryJpa(OrderRepository orderRepository, OrderEntityMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public Order newOrder(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        orderRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Order findOrderByOrderNumber(String orderNumber) {
        OrderEntity orderEntity = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return mapper.toDomain(orderEntity);
    }

    @Override
    public Order updateOrder(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        orderRepository.save(entity);
        return mapper.toDomain(entity);
    }
}
