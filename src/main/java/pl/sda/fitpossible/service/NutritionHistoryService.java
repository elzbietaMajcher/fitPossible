package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.NutritionHistoryDto;
import pl.sda.fitpossible.entity.NutritionHistory;
import pl.sda.fitpossible.repository.NutritonHistoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionHistoryService {

    private NutritonHistoryRepository nutritonHistoryRepository;

    public NutritionHistoryService(NutritonHistoryRepository nutritonHistoryRepository) {
        this.nutritonHistoryRepository = nutritonHistoryRepository;
    }

    public void createNutritionHistory(NutritionHistoryDto nutritionHistoryDto) {
        NutritionHistory nutritionHistory = mapTo(nutritionHistoryDto);
        nutritonHistoryRepository.save(nutritionHistory);
    }

    private NutritionHistory mapTo(NutritionHistoryDto nutritionHistoryDto) {
        NutritionHistory nutritionHistory = new NutritionHistory();
        nutritionHistory.setName(nutritionHistoryDto.getName());
        nutritionHistory.setCaloriesPerUnit(nutritionHistoryDto.getCaloriesPerUnit());
        nutritionHistory.setUnit(nutritionHistoryDto.getUnit());
        nutritionHistory.setMealTime(nutritionHistoryDto.getMealTime());
        return nutritionHistory;
    }

    private NutritionHistoryDto mapTo(NutritionHistory nutritionHistory) {
        NutritionHistoryDto nutritionHistoryDto = new NutritionHistoryDto();
        nutritionHistoryDto.setName(nutritionHistory.getName());
        nutritionHistoryDto.setCaloriesPerUnit(nutritionHistory.getCaloriesPerUnit());
        nutritionHistoryDto.setUnit(nutritionHistory.getUnit());
        nutritionHistoryDto.setMealTime(nutritionHistory.getMealTime());
        return nutritionHistoryDto;
    }

    public List<NutritionHistoryDto> findAll() {
        List<NutritionHistory> history = nutritonHistoryRepository.findAll();
        return history.stream().map(this::mapTo).collect(Collectors.toList());
    }

    public NutritionHistory findOneMeal(Long id) {
        return nutritonHistoryRepository.getOne(id);
    }

    public void update(Long id, NutritionHistoryDto nutritionHistoryDto) {
        NutritionHistory nutritionHistory = nutritonHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));
        nutritionHistory.setMealTime(nutritionHistoryDto.getMealTime());
    }

}


