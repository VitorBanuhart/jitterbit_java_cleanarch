package com.teste.jitterbit.application.gateways;

import com.teste.jitterbit.domain.entitties.Order.Order;

import java.util.List;

public interface RepositoryOrder {
    Order newOrder(Order order);

    List<Order> listOrders();

    Order findOrderByOrderNumber(String orderNumber);

    Order updateOrder(Order order);
}
