package lt.techin.Running.Club.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lt.techin.Running.Club.model.Role;

import java.util.List;

public record UserResponseDTO(
        @NotNull
        long id,
        @NotNull
        @Size(min = 4, max = 100, message = "at least 4 characters long")
        String username,
        @NotNull
        List<Role> roles

) {
}
