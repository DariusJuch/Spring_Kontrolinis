package lt.techin.Running.Club.controller;


import jakarta.validation.Valid;
import lt.techin.Running.Club.dto.RunningEventMapper;
import lt.techin.Running.Club.dto.RunningEventRequestDTO;
import lt.techin.Running.Club.dto.RunningEventResponseDTO;
import lt.techin.Running.Club.model.RunningEvent;
import lt.techin.Running.Club.service.RunningEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RunningEventController {

  private final RunningEventService runningEventService;

  @Autowired
  public RunningEventController(RunningEventService runningEventService) {
    this.runningEventService = runningEventService;
  }

  @GetMapping("/events")
  public ResponseEntity<List<RunningEvent>> getEvents() {
    return ResponseEntity.ok(runningEventService.findAllRunningEvents());
  }

  @GetMapping("/events/{eventId}")
  public ResponseEntity<RunningEventResponseDTO> getEvent(@PathVariable long eventId) {
    Optional<RunningEvent> event = runningEventService.findById(eventId);
    if (event.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    RunningEventResponseDTO runningEventResponseDTO = RunningEventMapper.toRunningEventDTO(event.get());
    return ResponseEntity.ok(runningEventResponseDTO);
  }

  @PostMapping("/events")
  public ResponseEntity<RunningEventResponseDTO> saveEvent(@Valid @RequestBody RunningEventRequestDTO runningEventRequestDTO) {
    RunningEvent event = RunningEventMapper.toRunningEvent(runningEventRequestDTO);
    RunningEvent savedEvent = runningEventService.saveRunningEvent(event);

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedEvent.getId())
                            .toUri())
            .body(RunningEventMapper.toRunningEventDTO(savedEvent));

  }

  @DeleteMapping("events/{eventId}")
  public ResponseEntity<Void> deleteEvent(@PathVariable long eventId) {
    if (!runningEventService.existRunningEventById(eventId)) {
      return ResponseEntity.notFound().build();
    }
    runningEventService.deleteRunningEventById(eventId);

    return ResponseEntity.noContent().build();
  }
}
