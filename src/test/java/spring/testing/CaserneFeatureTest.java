package spring.testing;

import io.cucumber.java.en.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import spring.testing.entity.Fire;

import spring.testing.repository.FireRepository;


import java.time.Instant;

import java.util.List;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@DataJpaTest
@SpringBootTest
@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class CaserneFeatureTest {

    @Autowired
    private FireRepository fireRepository;


    private Optional<Fire> oldestFire;

    @Given("the following fires:")
    public void givenFirestoVeteran(List<List<String>> firesData) {
        for (List<String> fireData : firesData) {
            String name = fireData.get(0);
            String year = fireData.get(1);
            Instant date = Instant.parse(year + "-01-01T00:00:00.000Z");
            Fire fire = new Fire(8, date, name);
            fireRepository.saveAndFlush(fire);
            System.out.println("Fire given: " + fireData);
        }
    // pour parser une date iso:  String dateString = "1948-01-01T00:00:00.000Z"; Instant date = Instant.parse(dateString);;
    // pour parser une date au format YYYY; int year = 1968; Instant date = Instant.parse(year + "-01-01T00:00:00.000Z");;
    // Ajouter un feu manuellement
    String name = "Bretagne";
    int year = 1968;
    Instant date = Instant.parse(year + "-01-01T00:00:00.000Z");
    Fire fire = new Fire(8, date, name);
    fireRepository.saveAndFlush(fire);
    System.out.println("Fire saved: " + fire);
    }
    

    @When("I look for oldest fire")
    public void selectOldestFire() {
        oldestFire = fireRepository.getOldestFire();
    }

    @Then("{int} fire(s) returned and name is {string}")
    public void assertFires(int count, String name) {
        assertTrue(oldestFire.isPresent());
        assertEquals(count, 1);
        assertEquals(name, oldestFire.get().getName());
    }

}
