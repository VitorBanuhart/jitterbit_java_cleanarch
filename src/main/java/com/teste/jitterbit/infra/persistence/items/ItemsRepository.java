package com.teste.jitterbit.infra.persistence.items;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<ItemsEntity, Long> {
}
