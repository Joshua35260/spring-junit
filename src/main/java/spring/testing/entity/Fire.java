package spring.testing.entity;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;


@Entity
public class Fire {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // @ManyToOne
  // private Fireman fireman;

  // ...
   @Min(value = 0, message = "severity must be positive")
  private int severity;
  private Instant date;
 
  private String name;
  private String location;
  private String fireYear;
  
 public Fire() {
  //Constructeur par défaut
 }
  public Fire(int severity, Instant date, String name) {
    this.severity = severity;
    this.date = date;
    this.name = name;
  }
  public Fire(String name, String fireYear, int severity, String location) {
    this.name = name;
    this.fireYear = fireYear;
    this.severity = severity;
    this.location = location;
}


  // public Fire(String name2, int intensity) {
  // }

  public Long getId() {
    return id;
  }

  public int getSeverity() {
    return severity;
  }

  public Instant getDate() {
    return date;
  }
  // public void setFireman(Fireman fireman) {
  //   this.fireman = fireman;
  // }

  // public Fireman getFireman() {
  //   return fireman;
  // }

  public String getYear() {
    return fireYear;
  }

  public String getName() {
    return name;
}

  public boolean isPresent() {
    return false;
  }
  public String getLocation() {
    return location;
  }



}
