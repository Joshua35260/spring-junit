package spring.testing;

// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@SpringBootTest(classes = SpringTestingApplication.class)
// @DataJpaTest
public class CucumberSpringConfiguration {
}