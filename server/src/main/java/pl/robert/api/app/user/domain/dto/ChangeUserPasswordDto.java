package pl.robert.api.app.user.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static pl.robert.api.app.shared.Constants.*;

@Getter @Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeUserPasswordDto {

    @NotEmpty(message = M_PASSWORD_EMPTY)
    @Size(min = COL_LENGTH_MIN_PASSWORD, max = COL_LENGTH_MAX_PASSWORD, message = M_PASSWORD_LENGTH)
    String password;

    @NotEmpty(message = M_CONFIRMED_PASSWORD_EMPTY)
    String confirmedPassword;
}
