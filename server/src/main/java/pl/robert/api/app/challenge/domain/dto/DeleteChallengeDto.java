package pl.robert.api.app.challenge.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteChallengeDto {

    Long challengeId;
    String defenderUsername;
}
