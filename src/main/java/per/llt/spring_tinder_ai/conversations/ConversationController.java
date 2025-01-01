package per.llt.spring_tinder_ai.conversations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import per.llt.spring_tinder_ai.profiles.ProfileRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class ConversationController {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping("/conversations")
    // I want a new conversation with someone but not sending message
    public Conversation createNewConversation(@RequestBody ConversationCreateRequest request) {

        //before we do create conversation , we need to check the profile is existed or not
        profileRepository.findById(request.profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to Find a Profile with " + request.profileId));

        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId,
                new ArrayList<>()
        );

        conversationRepository.save(conversation);
        return conversation;
    }


    /**
     * Sending message to specified profile with conversationId
     **/
    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId, @RequestBody ChatMessages chatMessages) {

        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to Find Conversation" + conversationId));

        profileRepository.findById(chatMessages.authorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to Find a Profile with " + chatMessages.authorId()));

        //TODO : need to validate that the author of a message happens to be only the profile associated with the name
        ChatMessages chatMessagesWithTime = new ChatMessages(chatMessages.messageText(),
                chatMessages.authorId(),
                LocalDateTime.now());

        conversation.messages().add(chatMessagesWithTime);

        conversationRepository.save(conversation);
        return conversation;
    }

    public record ConversationCreateRequest(
            String profileId
    ) {
    }
}
