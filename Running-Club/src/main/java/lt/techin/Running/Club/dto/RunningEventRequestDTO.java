package lt.techin.Running.Club.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RunningEventRequestDTO(
        @NotNull
        @Size(min = 5, max = 255, message = "Must be at least 6 characters long")
        String name,
        @NotNull
        @Future(message = "Calendar date must be in the future")
        LocalDate calendarDate,
        @NotNull
        @Pattern(regexp = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$")
        String location,
        @Min(value = 1, message = "Max participants cannot be 0")
        int maxParticipants
) {


}
