package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fitpossible.entity.Weight;

public interface WeightRepository extends JpaRepository<Weight, Long> {

}
