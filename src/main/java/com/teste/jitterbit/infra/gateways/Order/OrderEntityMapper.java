package com.teste.jitterbit.infra.gateways.Order;

import com.teste.jitterbit.domain.entitties.Items.Item;
import com.teste.jitterbit.domain.entitties.Order.Order;
import com.teste.jitterbit.infra.gateways.Item.ItemEntityMapper;
import com.teste.jitterbit.infra.persistence.items.ItemsEntity;
import com.teste.jitterbit.infra.persistence.order.OrderEntity;

import java.util.List;

public class OrderEntityMapper {

    private final ItemEntityMapper itemMapper = new ItemEntityMapper();

    public OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderNumber(order.getOrderNumber());
        entity.setValue(order.getValue());
        entity.setCreationDate(order.getCreationDate());

        if (order.getItems() != null) {
            List<ItemsEntity> itemsEntities = order.getItems().stream()
                    .map(itemDomain -> {
                        ItemsEntity ie = new ItemsEntity();
                        ie.setProductId(itemDomain.getProductId());
                        ie.setQuantity(itemDomain.getQuantity());
                        ie.setValue(itemDomain.getPrice());

                        ie.setOrder(entity);
                        return ie;
                    }).toList();
            entity.setItems(itemsEntities);
        }
        return entity;
    }

    public Order toDomain(OrderEntity entity) {
        List<Item> domainItems = entity.getItems().stream()
                .map(itemMapper::toDomain)
                .toList();
        return new Order(entity.getOrderNumber(),entity.getValue(), entity.getCreationDate(), domainItems);
    }
}
