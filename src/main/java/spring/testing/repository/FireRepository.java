package spring.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.testing.entity.Fire;


public interface FireRepository extends JpaRepository<Fire, Long> {

}

