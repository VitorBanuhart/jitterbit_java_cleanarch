package com.teste.jitterbit.infra.persistence.items;

import com.teste.jitterbit.infra.persistence.order.OrderEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class ItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal value;
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity order;

    public ItemsEntity(){}

    public ItemsEntity(Long productId, Integer quantity, BigDecimal value) {
        this.productId = productId;
        this.quantity = quantity;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
