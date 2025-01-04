package per.llt.spring_tinder_ai.profiles;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

    /**
     * 🚀  an aggregation stage in MongoDB that retrieves random documents.
     */

    @Aggregation(pipeline = {"{ $sample: { size: 1 } }"})
    Profile getRandomProfile();
}
