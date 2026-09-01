package com.vishnurajeev.lifttrack.user;

import java.time.Instant;

public record RegisterResponse(
        Long id,
        String email,
        Instant createdAt
) {
}
