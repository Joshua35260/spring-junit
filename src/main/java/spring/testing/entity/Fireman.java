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

    public String getName() {
        return name;
    }

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
