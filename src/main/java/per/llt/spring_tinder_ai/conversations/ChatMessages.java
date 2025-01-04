package per.llt.spring_tinder_ai.conversations;

import java.time.LocalDateTime;

public record ChatMessages(String messageText,
                           String authorId,  /** 🌟a person who wrote the message 🌟 **/
                           LocalDateTime messageTime) {
}
