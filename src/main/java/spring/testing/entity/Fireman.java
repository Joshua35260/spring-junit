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
  private int age;
  private int experience;

  @OneToMany(cascade = CascadeType.PERSIST)
  private List<Fire> fires;


public Fireman() {
  //Constructeur par défaut
}
  public Fireman(String name, int age, int experience) {
    this.name = name;
  }
  public Fireman(String name, int age) {
    this.name = name;
    this.age = age;
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
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public int getExperience() {
    return experience;
  }
  public void setExperience(int experience) {
    this.experience = experience;
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

  public void setFire(Fire fire) {
    getFires().add(fire);
  }
}
