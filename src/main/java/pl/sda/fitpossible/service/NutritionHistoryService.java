package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.NutritionHistoryDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.NutritionHistory;
import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.repository.NutritonHistoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionHistoryService {
    @Autowired
    private AppUserRepository appUserRepository;
    private NutritonHistoryRepository nutritonHistoryRepository;

    public NutritionHistoryService(NutritonHistoryRepository nutritonHistoryRepository) {
        this.nutritonHistoryRepository = nutritonHistoryRepository;
    }

    public void createNutritionHistory(NutritionHistoryDto nutritionHistoryDto, String login) {
        NutritionHistory nutritionHistory = mapTo(nutritionHistoryDto);
        AppUser owner = findUser(login);
        nutritionHistory.setUser(owner);
        nutritonHistoryRepository.save(nutritionHistory);
    }


    public List<NutritionHistoryDto> findAll(String login) {
        List<NutritionHistory> history = nutritonHistoryRepository.findAllByUserLogin(login);
        return history.stream().map(this::mapTo).collect(Collectors.toList());
    }

    public NutritionHistoryDto findOneMeal(Long id) {
        NutritionHistory nutritionHistory = nutritonHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));
        return mapTo(nutritionHistory);
    }

    public void update(Long id, NutritionHistoryDto nutritionHistoryDto) {
        NutritionHistory nutritionHistory = nutritonHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));
        nutritionHistory.setMealTime(nutritionHistoryDto.getMealTime());
        nutritonHistoryRepository.save(nutritionHistory);
    }

    private AppUser findUser(String login) {
        return appUserRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("AppUser" + login + " not found."));

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

}


