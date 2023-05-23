package spring.testing;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import spring.testing.entity.Fireman;
import spring.testing.repository.FiremanRepository;

@DataJpaTest
@SpringBootTest
@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class VeteranFeatureTest {
    @Autowired
    private FiremanRepository firemanRepository;

    private String oldestFiremanName;
    private int oldestAge;

    @Given("the following firemans worked on the fire in Corsica:")
    public void givenFollowingFiremans(DataTable dataTable) {
        List<Map<String, String>> firemanData = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> fireman : firemanData) {
            String name = fireman.get("Fireman Name");
            int age = Integer.parseInt(fireman.get("Fireman Age"));
            // Traitez les données des pompiers comme requis dans votre scénario
            Fireman newFireman = new Fireman(name, age);
            firemanRepository.saveAndFlush(newFireman);
        }
    }
    
    @When("I retrieve the oldest fireman who intervened on a fire in Corsica")
    public void whenIRetrieveTheOldestFiremanInCorsica() {
        oldestAge = Integer.MIN_VALUE; // Initialisez oldestAge avec une valeur minimale
        oldestFiremanName = "";
    
        // Parcourez la liste ou la structure de données contenant les pompiers
        // et trouvez le pompier le plus ancien
        for (Fireman fireman : firemanRepository.findAll()) {
            int age = fireman.getAge();
            if (age > oldestAge) { // Vérifiez si l'âge est supérieur à oldestAge
                oldestAge = age;
                oldestFiremanName = fireman.getName();
            }
        }
    
        // Utilisez les valeurs `oldestFiremanName` et `oldestAge` pour les assertions ou les actions nécessaires
    }
    
    

    @Then("the veteran fireman is {string} with age {int}")
    public void thenVeteranFiremanIs(String name, int age) {
        // Vérifier que le nom et l'âge du pompier le plus vieux correspondent aux valeurs attendues
        Assert.assertEquals(name, oldestFiremanName);
        Assert.assertEquals(age, oldestAge);
    }
}
