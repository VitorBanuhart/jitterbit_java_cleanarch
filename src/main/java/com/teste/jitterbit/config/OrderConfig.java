package com.teste.jitterbit.config;

import com.teste.jitterbit.application.gateways.RepositoryOrder;
import com.teste.jitterbit.application.usecases.Order.CreateOrder;
import com.teste.jitterbit.application.usecases.Order.FindOrderByOrderNumber;
import com.teste.jitterbit.application.usecases.Order.ListOrder;
import com.teste.jitterbit.infra.gateways.Order.OrderEntityMapper;
import com.teste.jitterbit.infra.gateways.Order.OrderRepositoryJpa;
import com.teste.jitterbit.infra.persistence.order.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Bean
    CreateOrder createOrder(RepositoryOrder orderRepository) {
        return new CreateOrder(orderRepository);
    }

    @Bean
    ListOrder listOrder(RepositoryOrder orderRepository) {
        return new ListOrder(orderRepository);
    }

    @Bean
    UpdateOrder updateOrder(RepositoryOrder orderRepository) {return new UpdateOrder(orderRepository);}

    @Bean
    FindOrderByOrderNumber findOrderByOrderNumber(RepositoryOrder orderRepository) {return new FindOrderByOrderNumber(orderRepository);}

    @Bean
    OrderRepositoryJpa createRepositoryJpa(OrderRepository orderRepository, OrderEntityMapper mapper){
        return new OrderRepositoryJpa(orderRepository, mapper);
    }

    @Bean
    OrderEntityMapper returnMapper() {
        return new OrderEntityMapper();
    }


}
