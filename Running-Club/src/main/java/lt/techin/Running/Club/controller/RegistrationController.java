package lt.techin.Running.Club.controller;


import jakarta.validation.Valid;
import lt.techin.Running.Club.dto.RegistrationMapper;
import lt.techin.Running.Club.dto.RegistrationRequestDTO;
import lt.techin.Running.Club.dto.RegistrationResponseDTO;
import lt.techin.Running.Club.model.Registration;
import lt.techin.Running.Club.model.RunningEvent;
import lt.techin.Running.Club.model.User;
import lt.techin.Running.Club.service.RegistrationService;
import lt.techin.Running.Club.service.RunningEventService;
import lt.techin.Running.Club.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegistrationController {

  UserService userService;
  RegistrationService registrationService;
  RunningEventService runningEventService;

  public RegistrationController(UserService userService, RegistrationService registrationService, RunningEventService runningEventService) {
    this.userService = userService;
    this.registrationService = registrationService;
    this.runningEventService = runningEventService;
  }

  @PostMapping("/events/{eventId}/register")
  public ResponseEntity<?> saveRegistration(@PathVariable long eventId,
                                            @Valid @RequestBody RegistrationRequestDTO registrationRequestDTO,
                                            Authentication authentication
  ) {
    Map<String, String> errors = new HashMap<>();
    long userIdAuth = ((User) authentication.getPrincipal()).getId();
    long userIdBody = registrationRequestDTO.user().getId();
    if (userIdAuth != userIdBody) {
      errors.put("message", "Can not register other users");
      return ResponseEntity.badRequest().body(errors);
    }
    Optional<User> user = userService.findById(userIdBody);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    Optional<RunningEvent> runningEvent = runningEventService.findById(eventId);
    if (runningEvent.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    Registration registration = registrationService.saveRegistration(new Registration(
            user.get(),
            runningEvent.get(),
            LocalDateTime.now()
    ));
    RegistrationResponseDTO registrationResponseDTO = RegistrationMapper.toRegistrationResponseDTO(registration);
    return ResponseEntity.ok(registrationResponseDTO);
  }
}



