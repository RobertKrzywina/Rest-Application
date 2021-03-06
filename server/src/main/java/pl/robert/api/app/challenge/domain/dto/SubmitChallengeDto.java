package pl.robert.api.app.challenge.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static pl.robert.api.app.shared.Constants.M_DEFENDER_USERNAME_EMPTY;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubmitChallengeDto {

    String attackerUsername;

    @NotEmpty(message = M_DEFENDER_USERNAME_EMPTY)
    String defenderUsername;

    List<Long> questionsIds = new ArrayList<>(5);

    List<Character> answers = new ArrayList<>(5);
}
