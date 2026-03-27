package com.teste.jitterbit.domain.entitties.Order;

import com.teste.jitterbit.domain.entitties.Items.Item;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class Order {
    private String orderNumber;
    private BigDecimal value;
    private ZonedDateTime creationDate;
    private List<Item> items;

    public Order(String orderNumber,BigDecimal value, ZonedDateTime creationDate, List<Item> items) {
        this.orderNumber = orderNumber;
        this.value = value;
        this.creationDate = creationDate;
        this.items = items;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

}
