package lt.techin.Running.Club.dto;

import lt.techin.Running.Club.model.RunningEvent;

import java.util.List;

public class RunningEventMapper {

  public static List<RunningEventRequestDTO> toRunningEventRequestDTOList(List<RunningEvent> runningEvents) {
    List<RunningEventRequestDTO> result = runningEvents.stream()
            .map(runningEvent -> new RunningEventRequestDTO(runningEvent.getName(), runningEvent.getCalendarDate(), runningEvent.getLocation(), runningEvent.getMaxParticipants()))
            .toList();

    return result;
  }

  public static List<RunningEventResponseDTO> toRunningEventResponseDTOList(List<RunningEvent> runningEvents) {
    List<RunningEventResponseDTO> result = runningEvents.stream()
            .map(runningEvent -> new RunningEventResponseDTO(runningEvent.getId(), runningEvent.getName(), runningEvent.getCalendarDate(), runningEvent.getLocation(), runningEvent.getMaxParticipants()))
            .toList();

    return result;
  }

  public static RunningEventResponseDTO toRunningEventDTO(RunningEvent runningEvent) {
    return new RunningEventResponseDTO(
            runningEvent.getId(),
            runningEvent.getName(),
            runningEvent.getCalendarDate(),
            runningEvent.getLocation(),
            runningEvent.getMaxParticipants()
    );
  }

  public static RunningEvent toRunningEvent(RunningEventRequestDTO runningEventRequestDTO) {
    return new RunningEvent(
            runningEventRequestDTO.name(),
            runningEventRequestDTO.calendarDate(),
            runningEventRequestDTO.location(),
            runningEventRequestDTO.maxParticipants()
    );
  }


}
