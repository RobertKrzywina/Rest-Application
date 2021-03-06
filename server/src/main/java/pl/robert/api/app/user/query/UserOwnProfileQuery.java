package pl.robert.api.app.user.query;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserOwnProfileQuery {

    String username;
    String email;
    String roles;
    String level;
    String currentRank;
    String experience;
    String leftExperienceToTheNextLevel;
    String currentExperienceInPercents;
    String numberOfWins;
    String numberOfLoses;
    String numberOfDraws;
    String numberOfPosts;
    String numberOfComments;
    String numberOfVotes;
}
