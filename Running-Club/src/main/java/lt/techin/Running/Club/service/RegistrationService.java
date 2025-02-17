package lt.techin.Running.Club.service;


import lt.techin.Running.Club.model.Registration;
import lt.techin.Running.Club.model.RunningEvent;
import lt.techin.Running.Club.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

  private final RegistrationRepository registrationRepository;

  @Autowired
  public RegistrationService(RegistrationRepository registrationRepository) {
    this.registrationRepository = registrationRepository;
  }

  public Registration saveRegistration(Registration registration) {
    return registrationRepository.save(registration);
  }

  public Optional<Registration> getRegistrationById(long id) {
    return registrationRepository.findById(id);
  }

  public List<Registration> getRegistrations() {
    return registrationRepository.findAll();
  }

}
