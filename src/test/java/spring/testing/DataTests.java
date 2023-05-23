package spring.testing;
// package spring.testing;

// import java.time.Instant;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;

// import spring.testing.controller.FiremanController;
// import spring.testing.entity.Fire;
// import spring.testing.entity.Fireman;
// import spring.testing.repository.FireRepository;
// import spring.testing.repository.FiremanRepository;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// // dit à Spring Data que c'est un test de data et qu'il faut initialiser la fausse base de données
// @DataJpaTest
// class DataTests {

//   // on peut ensuite injecter un repository et l'utiliser normalement
//   @Autowired
//   private FireRepository fireRepository;

//   @Autowired
//   private FiremanRepository firemanRepository;
//   // Écrire vos tests ici

//   @Test
//   public void testCreateFire() {
//     int severity = 8;
//     Instant date = Instant.now();
//     var fire = new Fire(severity, date);

//     // flush envoie les données instantanément à la base
//     fireRepository.saveAndFlush(fire);

//     Optional<Fire> fromDB = fireRepository.findById(fire.getId());

//     assertTrue(fromDB.isPresent());
//     assertEquals(fire.getId(), fromDB.get().getId());
//     assertEquals(date, fromDB.get().getDate());
//     assertEquals(severity, fromDB.get().getSeverity());
//   }

//   @Test
//   public void testCreateFires() {
//     // Création des feux
//     int severity1 = 8;
//     Instant date1 = Instant.now();
//     Fire fire1 = new Fire(severity1, date1);

//     int severity2 = 6;
//     Instant date2 = Instant.now().minusSeconds(3600); // Une heure avant le premier feu
//     Fire fire2 = new Fire(severity2, date2);

//     // Sauvegarde des feux dans la base de données
//     fireRepository.saveAndFlush(fire1);
//     fireRepository.saveAndFlush(fire2);

//     // Création du pompier associé aux feux
//     Fireman fireman = new Fireman("John Doe");
//     fireman.getFires().add(fire1);
//     fireman.getFires().add(fire2);

//     // Sauvegarde du pompier dans la base de données
//     firemanRepository.saveAndFlush(fireman);

//     // Récupération du pompier de la base de données
//     Optional<Fireman> retrievedFireman = firemanRepository.findById(fireman.getId());
//     assertTrue(retrievedFireman.isPresent());

//     // Vérification des données du pompier
//     assertEquals(fireman.getId(), retrievedFireman.get().getId());
//     assertEquals(fireman.getName(), retrievedFireman.get().getName());

//     // Vérification de la liste de feux du pompier
//     List<Fire> fireList = retrievedFireman.get().getFires();
//     assertEquals(2, fireList.size());
//     assertTrue(fireList.contains(fire1));
//     assertTrue(fireList.contains(fire2));
//   }

//   @Test
//   public void NegativFire() {
//     int severity = -8;
//     Instant date = Instant.now();
//     var fire = new Fire(severity, date);

//     // flush envoie les données instantanément à la base
//     fireRepository.saveAndFlush(fire);

//     Optional<Fire> fromDB = fireRepository.findById(fire.getId());

//     assertTrue(fromDB.isPresent());
//     assertEquals(fire.getId(), fromDB.get().getId());
//     assertEquals(date, fromDB.get().getDate());
//     assertEquals(severity, fromDB.get().getSeverity());
//   }

//   @Test
//   public void testGetVeteranWithMultipleFiremen() {
//     // Créez plusieurs Firemen avec différents nombres de feux à leur actif
//     Fireman fireman1 = new Fireman("John Doe");
//     Fireman fireman2 = new Fireman("Jane Smith");
//     Fireman fireman3 = new Fireman("Mike Johnson");

//     Fire fire1 = new Fire(8, Instant.now());
//     Fire fire2 = new Fire(6, Instant.now());
//     Fire fire3 = new Fire(7, Instant.now());
//     Fire fire4 = new Fire(9, Instant.now());
//     Fire fire5 = new Fire(5, Instant.now());
//     Fire fire6 = new Fire(6, Instant.now());
//     Fire fire7 = new Fire(8, Instant.now());
//     Fire fire8 = new Fire(7, Instant.now());
//     Fire fire9 = new Fire(8, Instant.now());

//     fireman1.getFires().add(fire1);
//     fireman1.getFires().add(fire2);
//     fireman1.getFires().add(fire3);

//     fireman2.getFires().add(fire4);
//     fireman2.getFires().add(fire5);
//     fireman2.getFires().add(fire6);
//     fireman2.getFires().add(fire7);

//     fireman3.getFires().add(fire8);
//     fireman3.getFires().add(fire9);

//     fire1.setFireman(fireman1);
//     fire2.setFireman(fireman1);
//     fire3.setFireman(fireman1);
//     fire4.setFireman(fireman2);
//     fire5.setFireman(fireman2);
//     fire6.setFireman(fireman2);
//     fire7.setFireman(fireman2);
//     fire8.setFireman(fireman3);
//     fire9.setFireman(fireman3);

//     firemanRepository.saveAndFlush(fireman1);
//     firemanRepository.saveAndFlush(fireman2);
//     firemanRepository.saveAndFlush(fireman3);

//     // Appelez la méthode getVeteran() pour récupérer le pompier le plus chevronné
//     Optional<Fireman> veteran = firemanRepository.getVeteran();

//     // Vérifiez que le pompier retourné est le bon
//     assertTrue(veteran.isPresent());
//     assertEquals("Jane Smith", veteran.get().getName());
//   }

//   @Test
//   public void testGetVeteranWithNoFireman() {
//     // Appelez la méthode getVeteran() lorsque la base ne contient aucun pompier
//     Optional<Fireman> veteran = firemanRepository.getVeteran();

//     // Vérifiez que l'Optional est vide (empty)
//     assertFalse(veteran.isPresent());
//   }

//   @Test
//   public void testGetVeteranWithSingleFireman() {
//     // Créez un seul Fireman avec quelques feux à son actif
//     Fireman fireman = new Fireman("John Doe");

//     fireman.getFires().add(new Fire(8, Instant.now()));
//     fireman.getFires().add(new Fire(6, Instant.now()));
//     fireman.getFires().add(new Fire(7, Instant.now()));

//     firemanRepository.saveAndFlush(fireman);

//     // Appelez la méthode getVeteran() pour récupérer le pompier le plus chevronné
//     Optional<Fireman> veteran = firemanRepository.getVeteran();

//     // Vérifiez que le pompier retourné est le bon
//     assertTrue(veteran.isPresent());
//     assertEquals("John Doe", veteran.get().getName());
//   }

//   @WebMvcTest(FiremanController.class)
//   class ControllersTests {
  
//       @Autowired
//       MockMvc mockMvc;
  
//       @MockBean
//       FiremanRepository firemanRepository;
  
//       @Test
//       void testGetVeteranSimple() throws Exception {
//           var fireman = mock(Fireman.class);
//           when(fireman.getId()).thenReturn(1L);
//           when(fireman.getName()).thenReturn("champion");
//           when(firemanRepository.getVeteran()).thenReturn(Optional.of(fireman));
  
//           mockMvc.perform(get("/fireman/veteran"))
//                   .andExpect(status().isOk())
//                   .andExpect(jsonPath("$.id").value(fireman.getId()))
//                   .andExpect(jsonPath("$.name").value("champion"));
//       }
  
//       @Test
//       void testGetVeteranNotFound() throws Exception {
//           // Configurez le comportement du repository pour retourner Optional.empty()
//           when(firemanRepository.getVeteran()).thenReturn(Optional.empty());
  
//           mockMvc.perform(get("/fireman/veteran"))
//                   .andExpect(status().isNotFound());
//       }
//       @Test
//       void testGetStatsWithMultipleFiremenAndFires() throws Exception {
//           Fireman fireman1 = new Fireman();
//           fireman1.setId(1L);
//           fireman1.setName("Fireman 1");
//           fireman1.setFires(List.of(new Fire(), new Fire()));
  
//           Fireman fireman2 = new Fireman();
//           fireman2.setId(2L);
//           fireman2.setName("Fireman 2");
//           fireman2.setFires(List.of(new Fire(), new Fire(), new Fire()));
  
//           List<Fireman> firemen = List.of(fireman1, fireman2);
  
//           when(firemanRepository.findAll()).thenReturn(firemen);
  
//           mockMvc.perform(get("/fireman/stats"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.firemanCount").value(2))
//               .andExpect(jsonPath("$.uniqueFiresCount").value(5));
//       }
  
//       @Test
//       void testGetStatsWithDuplicateFires() throws Exception {
//           Fireman fireman1 = new Fireman();
//           fireman1.setId(1L);
//           fireman1.setName("Fireman 1");
//           fireman1.setFires(List.of(new Fire(), new Fire()));
  
//           Fireman fireman2 = new Fireman();
//           fireman2.setId(2L);
//           fireman2.setName("Fireman 2");
//           fireman2.setFires(List.of(new Fire(), new Fire()));
  
//           List<Fireman> firemen = List.of(fireman1, fireman2);
  
//           when(firemanRepository.findAll()).thenReturn(firemen);
  
//           mockMvc.perform(get("/fireman/stats"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.firemanCount").value(2))
//               .andExpect(jsonPath("$.uniqueFiresCount").value(2));
//       }
  
//   }
  


// }
