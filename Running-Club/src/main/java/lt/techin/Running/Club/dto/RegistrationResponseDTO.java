package lt.techin.Running.Club.dto;

import java.time.LocalDateTime;

public record RegistrationResponseDTO(
        Long id,
        Long userId,
        String eventName,
        LocalDateTime registrationDate
) {
}