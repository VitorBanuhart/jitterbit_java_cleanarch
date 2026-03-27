package com.teste.jitterbit.infra.persistence.order;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
    @EntityGraph(attributePaths = {"items"})
    Optional<OrderEntity> findByOrderNumber(String orderNumber);
}
