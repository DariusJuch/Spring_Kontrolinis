package lt.techin.Running.Club.dto;

import jakarta.validation.constraints.NotNull;
import lt.techin.Running.Club.model.User;

public record RegistrationRequestDTO(
        @NotNull
        User user
) {


}
