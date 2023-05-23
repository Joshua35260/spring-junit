package spring.testing.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import spring.testing.dto.FiremanStatsDTO;
import spring.testing.entity.Fire;
import spring.testing.entity.Fireman;
import spring.testing.repository.FiremanRepository;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/fireman")
public class FiremanController {


    @Autowired
    FiremanRepository firemanRepository;

    record FiremanData(Long id, String name, int firesCount) {
        static FiremanData fromFireman(Fireman fireman) {
            return new FiremanData(fireman.getId(), fireman.getName(), fireman.getFires().size());
        }
    }

    @GetMapping("/veteran")
    public FiremanData getVeteran() {
        Optional<Fireman> veteranMaybe = firemanRepository.getVeteran();
        Fireman veteran = veteranMaybe.orElseThrow(() -> new NotFoundException());
        return FiremanData.fromFireman(veteran);
    }

    @GetMapping("/stats")
    public FiremanStatsDTO getStats() {
      List<Fireman> firemen = firemanRepository.findAll();
      Set<Long> uniqueFireIds = new HashSet<>();
      int firemanCount = firemen.size();
      int firesCount = 0;
  
      for (Fireman fireman : firemen) {
          List<Fire> fires = fireman.getFires();
          firesCount += fires.size();
  
          for (Fire fire : fires) {
              uniqueFireIds.add(fire.getId());
          }
      }
  
      int uniqueFiresCount = uniqueFireIds.size();
      return new FiremanStatsDTO(firemanCount, uniqueFiresCount);
  }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {
    }

  
}
