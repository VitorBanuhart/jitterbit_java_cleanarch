package com.teste.jitterbit.application.usecases.Order;

import com.teste.jitterbit.application.gateways.RepositoryOrder;
import com.teste.jitterbit.domain.entitties.Order.Order;

import java.util.List;

public class ListOrder {
    private final RepositoryOrder repository;

    public ListOrder(RepositoryOrder repository) {
        this.repository = repository;
    }

    public List<Order> listOrders() {
        return this.repository.listOrders();
    }
}
