package lt.techin.Running.Club.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lt.techin.Running.Club.model.Role;
import lt.techin.Running.Club.validation.UserName;

import java.util.List;

public record UserRequestDTO(
        @NotNull
        @Size(min = 4, max = 100, message = "at least 4 characters long")
        @UserName
        String username,
        @NotNull
        @Size(min = 6, max = 150, message = "at least 6 characters long")
        String password,
        @NotNull
        List<Role> roles
) {
}
