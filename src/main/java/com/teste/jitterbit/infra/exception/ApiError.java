package com.teste.jitterbit.infra.exception;

import java.time.LocalDateTime;

public record ApiError(
        String title,
        int status,
        String detail,
        LocalDateTime timestamp
) {
}
