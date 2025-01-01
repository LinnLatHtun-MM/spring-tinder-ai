package per.llt.spring_tinder_ai.profiles;

import org.springframework.data.annotation.Id;

/**
 * In programming languages, records might be simple key-value objects or tuples.
 * It lacks business logic.
 * Only deals with storing and representing data as it is.
 **/
public record Profile(
        @Id
        String id,
        String firstName,
        String lastName,
        int age,
        String ethnicity,
        Gender gender,
        String bio,
        String imageUrl,
        String myersBriggsPersonalityType
) {
}
