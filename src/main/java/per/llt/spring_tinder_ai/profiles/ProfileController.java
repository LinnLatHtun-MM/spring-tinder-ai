package per.llt.spring_tinder_ai.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {


    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/profiles/random")
    public Profile getRandomProfile()
    {
        Profile profile= profileRepository.getRandomProfile();
        System.out.println(profile);
        return profile;
    }
}
