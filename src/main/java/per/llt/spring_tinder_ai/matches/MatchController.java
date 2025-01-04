package per.llt.spring_tinder_ai.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import per.llt.spring_tinder_ai.conversations.Conversation;
import per.llt.spring_tinder_ai.conversations.ConversationRepository;
import per.llt.spring_tinder_ai.profiles.Profile;
import per.llt.spring_tinder_ai.profiles.ProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    /**
     * 🌟 Start with a person who is you would like to start a conversation him or her as a new match 🌟
     **/
    @PostMapping("/matches")
    public Match match(@RequestBody CreateMatchRequest request) {

        Profile profile = profileRepository.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid profile id: " + request.profileId()));

        //TODO: Make sure there are no existing conversations with this profile already
        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                profile.id(),
                new ArrayList<>()
        );

        conversationRepository.save(conversation);

        Match match = new Match(UUID.randomUUID().toString(), profile, conversation.id());
        matchRepository.save(match);

        return match;
    }


    /**
     * 🌟You match this profiles 🌟
     **/
    @GetMapping("/matches")
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
}
