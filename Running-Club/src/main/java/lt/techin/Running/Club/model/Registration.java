package lt.techin.Running.Club.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registrations")
public class Registration {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinColumn(name = "running_event_id")
  private RunningEvent runningEvent;

  private LocalDateTime registrationDate;

  public Registration() {

  }

  public Registration(User user, RunningEvent runningEvent, LocalDateTime registrationDate) {
    this.user = user;
    this.runningEvent = runningEvent;
    this.registrationDate = registrationDate;
  }

  public long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public RunningEvent getRunningEvent() {
    return runningEvent;
  }

  public void setRunningEvent(RunningEvent runningEvent) {
    this.runningEvent = runningEvent;
  }

  public LocalDateTime getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDateTime registrationDate) {
    this.registrationDate = registrationDate;
  }
}
