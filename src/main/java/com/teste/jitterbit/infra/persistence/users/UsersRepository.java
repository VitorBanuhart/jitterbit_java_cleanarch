package com.teste.jitterbit.infra.persistence.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<UsersEntity, String> {
    UserDetails findByLogin(String login);
}
