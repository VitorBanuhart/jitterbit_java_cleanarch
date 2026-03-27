package com.teste.jitterbit.infra.controller.Order;

import com.teste.jitterbit.domain.entitties.Items.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderDTO (
        @NotBlank(message = "OrderId não pode estar em branco")
        String orderId,
        @NotNull(message = "value não pode estar em branco")
        @Positive(message = "o valor deve ser maior que ZERO")
        BigDecimal value,
        @NotBlank(message = "creationDate não pode estar em branco")
        String creationDate,
        @NotEmpty(message = "a lista nao pode estar vazia")
        List<Item> items
){
}
