package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fitpossible.entity.NutritionHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface NutritionHistoryRepository extends JpaRepository<NutritionHistory, Long> {
    List<NutritionHistory> findAllByUserId(Long id);


    //
//    List<NutritionHistory> findAllByAppUserId(Long appUserId);
//
//
    List<NutritionHistory> findNutritionHistoryByMealTimeAfterAndAndUserId(LocalDateTime localDateTime, Long id);

    NutritionHistory findByFoodId(Long foodId);
    NutritionHistory findByMealTime(LocalDateTime localDateTime);


}

