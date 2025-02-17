package lt.techin.Running.Club.dto;

import lt.techin.Running.Club.model.Registration;
import lt.techin.Running.Club.service.RunningEventService;
import lt.techin.Running.Club.service.UserService;

public class RegistrationMapper {

  UserService userService;
  RunningEventService runningEventService;

  public RegistrationMapper(UserService userService, RunningEventService runningEventService) {
    this.userService = userService;
    this.runningEventService = runningEventService;
  }

  public static RegistrationResponseDTO toRegistrationResponseDTO(Registration registration) {
    return new RegistrationResponseDTO(
            registration.getId(),
            registration.getUser().getId(),
            registration.getRunningEvent().getName(),
            registration.getRegistrationDate()
    );
  }
}
