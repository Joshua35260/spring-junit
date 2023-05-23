package spring.testing.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Fireman {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @OneToMany(cascade = CascadeType.PERSIST)
  private List<Fire> fires;

  public Fireman() {
  }

  public Fireman(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // Autres méthodes et getters/setters...
  
  public List<Fire> getFires() {
    if (fires == null) {
      fires = new ArrayList<>();
    }
    return fires;
  }

  public void setFires(List<Fire> fires) {
    this.fires = fires;
  }
}
