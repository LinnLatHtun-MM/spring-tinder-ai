package per.llt.spring_tinder_ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import per.llt.spring_tinder_ai.conversations.ConversationRepository;
import per.llt.spring_tinder_ai.profiles.ProfileCreationService;
import per.llt.spring_tinder_ai.profiles.ProfileRepository;


@SpringBootApplication
public class SpringAiTinderApplication implements CommandLineRunner {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ProfileCreationService profileCreationService;


    public static void main(String[] args) {
        SpringApplication.run(SpringAiTinderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        clearAllData();
        profileCreationService.saveProfilesToDB();
    }

    private void clearAllData() {
        conversationRepository.deleteAll();
        profileRepository.deleteAll();
    }
}
