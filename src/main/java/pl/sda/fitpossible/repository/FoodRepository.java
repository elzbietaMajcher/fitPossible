package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.fitpossible.entity.Food;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{

    Optional<Food>findByName(String name);
}
