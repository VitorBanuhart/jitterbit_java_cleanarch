package com.teste.jitterbit.application.usecases.Order;
import com.teste.jitterbit.application.gateways.RepositoryOrder;
import com.teste.jitterbit.domain.entitties.Order.Order;

public class FindOrderByOrderNumber {
    private final RepositoryOrder repository;

    public FindOrderByOrderNumber(RepositoryOrder repository) {
        this.repository = repository;
    }

    public Order findOrderIdByOrderNumber (String orderNumber) {
        return repository.findOrderByOrderNumber(orderNumber);
    }
}