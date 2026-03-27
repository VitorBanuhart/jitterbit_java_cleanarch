package com.teste.jitterbit.infra.controller.Authentication;

import com.teste.jitterbit.domain.entitties.Users.UserRoles;

public record RegisterDTO(
        String login,
        String password,
        UserRoles role
) {
}
