package com.teste.jitterbit.infra.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiGlobalError(
        int status,
        String message,
        LocalDateTime timestamp,
        List<String> errors
) {
}
