package com.teste.jitterbit.infra.gateways.Item;

import com.teste.jitterbit.domain.entitties.Items.Item;
import com.teste.jitterbit.infra.persistence.items.ItemsEntity;

public class ItemEntityMapper {
    public ItemsEntity toEntity(Item item) {
        return new ItemsEntity(item.getProductId(), item.getQuantity(), item.getPrice());
    }

    public Item toDomain(ItemsEntity entity) {
        return new Item(entity.getProductId(), entity.getQuantity(), entity.getValue());
    }
}
