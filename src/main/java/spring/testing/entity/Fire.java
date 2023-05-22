package spring.testing.entity;

import java.time.Instant;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;


@Entity
public class Fire {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Fireman fireman;

  // ...
   @Min(value = 0, message = "severity must be positive")
  private int severity;
  private Instant date;


  public Fire(int severity, Instant date) {
    this.severity = severity;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public int getSeverity() {
    return severity;
  }

  public Instant getDate() {
    return date;
  }
  public void setFireman(Fireman fireman) {
    this.fireman = fireman;
  }

  public Fireman getFireman() {
    return fireman;
  }
}
