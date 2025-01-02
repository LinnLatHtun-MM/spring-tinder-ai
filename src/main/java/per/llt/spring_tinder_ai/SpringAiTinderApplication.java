package per.llt.spring_tinder_ai;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import per.llt.spring_tinder_ai.conversations.ChatMessages;
import per.llt.spring_tinder_ai.conversations.Conversation;
import per.llt.spring_tinder_ai.conversations.ConversationRepository;
import per.llt.spring_tinder_ai.profiles.Profile;
import per.llt.spring_tinder_ai.profiles.Gender;
import per.llt.spring_tinder_ai.profiles.ProfileRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringAiTinderApplication implements CommandLineRunner {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private OpenAiChatClient openAiChatClient;

    public static void main(String[] args) {
        SpringApplication.run(SpringAiTinderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //I want to be clear all conversations because of using AI when creating a branch of profiles.
        profileRepository.deleteAll();
        conversationRepository.deleteAll();

        Profile profile = new Profile(
                "1",
                "lin",
                "lat",
                25,
                "Myanmar",
                Gender.FEMALE,
                "Software programmer",
                "foo.jpg",
                "INTP"
        );

        Profile profile1 = new Profile(
                "2",
                "Tun",
                "Myat",
                35,
                "Myanmar",
                Gender.FEMALE,
                "Software programmer",
                "foo.jpg",
                "INTP"
        );

        profileRepository.save(profile);
        profileRepository.save(profile1);
        profileRepository.findAll().forEach(System.out::println);


        Conversation conversation = new Conversation(
                "1",
                profile.id(),
                List.of(new ChatMessages("Hello", profile.id(), LocalDateTime.now()))
        );

        conversationRepository.save(conversation);
        conversationRepository.findAll().forEach(System.out::println);
    }
}
