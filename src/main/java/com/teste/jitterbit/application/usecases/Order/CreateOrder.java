package com.teste.jitterbit.application.usecases.Order;

import com.teste.jitterbit.application.gateways.RepositoryOrder;
import com.teste.jitterbit.domain.entitties.Order.Order;

public class CreateOrder {
    private final RepositoryOrder repository;

    public CreateOrder(RepositoryOrder repository) {
        this.repository = repository;
    }

    public Order createNewOrder(Order order) {
        return repository.newOrder(order);
    }
}
