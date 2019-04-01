package pl.robert.api.app.user.query;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter @Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserQuery {

    String username;
    String email;
    String password;
    String roles;
}