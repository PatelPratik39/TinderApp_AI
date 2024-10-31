package com.ai.tinder_ai_backend.conversations;

import java.time.LocalDateTime;

public record ChatMessages(
        String messageText,
        String authorId,
        LocalDateTime messageTime
) {
}
