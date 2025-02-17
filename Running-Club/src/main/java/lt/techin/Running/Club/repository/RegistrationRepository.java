package lt.techin.Running.Club.repository;

import lt.techin.Running.Club.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {


}
