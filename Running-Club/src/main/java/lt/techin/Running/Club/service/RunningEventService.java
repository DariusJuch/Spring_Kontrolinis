package lt.techin.Running.Club.service;


import lt.techin.Running.Club.model.RunningEvent;
import lt.techin.Running.Club.repository.RunningEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RunningEventService {

  private final RunningEventRepository runningEventRepository;

  @Autowired
  public RunningEventService(RunningEventRepository runningEventRepository) {
    this.runningEventRepository = runningEventRepository;
  }

  public RunningEvent saveRunningEvent(RunningEvent runningEvent) {
    return runningEventRepository.save(runningEvent);
  }

  public List<RunningEvent> findAllRunningEvents() {
    return runningEventRepository.findAll();
  }

  public Optional<RunningEvent> findById(long id) {
    return runningEventRepository.findById(id);
  }

  public boolean existRunningEventById(long id) {
    return runningEventRepository.existsById(id);
  }

  public void deleteRunningEventById(long id) {
    runningEventRepository.deleteById(id);
  }
}
