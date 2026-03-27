package com.teste.jitterbit.infra.persistence.order;

import com.teste.jitterbit.infra.persistence.items.ItemsEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderNumber;
    private BigDecimal value;
    private ZonedDateTime creationDate;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ItemsEntity> items;

    public OrderEntity() {}

    public void updateOrder(BigDecimal value, ZonedDateTime creationDate, List<ItemsEntity> items) {
        this.value = value;
        this.creationDate = creationDate;
        this.items = items;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setItems(List<ItemsEntity> items) {
        this.items = items;
    }
}
