package spring.testing.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spring.testing.entity.Fire;

public class FireService {

    private List<Fire> fires;

    public FireService() {
        this.fires = new ArrayList<>(fires);
    }

    public List<Fire> getOldestFires(int count) {
      // Sort the fires by year in ascending order
      Collections.sort(fires, (fire1, fire2) -> fire1.getYear().compareTo(fire2.getYear()));
  
      // Return the oldest fires based on the count
      return fires.subList(0, Math.min(count, fires.size()));
  }
  
}