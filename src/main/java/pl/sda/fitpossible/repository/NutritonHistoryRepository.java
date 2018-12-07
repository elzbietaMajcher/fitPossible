package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fitpossible.entity.NutritionHistory;

import java.util.List;

public interface NutritonHistoryRepository extends JpaRepository<NutritionHistory, Long> {

    List<NutritionHistory> findAllByUserLogin(String login);
}

