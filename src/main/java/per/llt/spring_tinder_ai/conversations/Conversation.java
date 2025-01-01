package per.llt.spring_tinder_ai.conversations;

import java.util.List;

public record Conversation(
        String id,
        String profileId,
        List<ChatMessages> messages
) {
}
