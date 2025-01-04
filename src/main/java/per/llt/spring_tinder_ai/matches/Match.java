package per.llt.spring_tinder_ai.matches;

import per.llt.spring_tinder_ai.profiles.Profile;


public record Match(String matchId, Profile profile, String conversationId) {

}
