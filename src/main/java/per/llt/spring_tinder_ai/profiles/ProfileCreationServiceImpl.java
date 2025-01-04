package per.llt.spring_tinder_ai.profiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

@Service
public class ProfileCreationServiceImpl implements ProfileCreationService {

    @Value("#{${tinderai.character.user}}")
    private Map<String, String> userProfileProperties;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void saveProfilesToDB() {
        Gson gson = new Gson();

        try {
            List<Profile> existingProfile = gson.fromJson(
                    new FileReader(new File(getClass().getClassLoader().getResource("men/profiles.json").getFile())),
                    new TypeToken<List<Profile>>() {}.getType()
            );

            profileRepository.deleteAll();
            profileRepository.saveAll(existingProfile);
            System.out.println(existingProfile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Profiles file not found");
        }

        /**
         * 🌟 Creates a new Profile tailored just for you! 🌟
         */
        Profile profile = new Profile(
                userProfileProperties.get("id"),
                userProfileProperties.get("firstName"),
                userProfileProperties.get("lastName"),
                Integer.parseInt(userProfileProperties.get("age")),
                userProfileProperties.get("ethnicity"),
                Gender.valueOf(userProfileProperties.get("gender")),
                userProfileProperties.get("bio"),
                userProfileProperties.get("imageUrl"),
                userProfileProperties.get("myersBriggsPersonalityType"));

        System.out.println(userProfileProperties);
        profileRepository.save(profile);
    }


}
