package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.NutritionHistoryDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.NutritionHistory;
import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.repository.NutritonHistoryRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionHistoryService {
    @Autowired
    private AppUserRepository appUserRepository;
    private NutritonHistoryRepository nutritonHistoryRepository;
    @Autowired
    private FoodService foodService;
    @Autowired
    private AppUserService appUserService;

    public NutritionHistoryService(NutritonHistoryRepository nutritonHistoryRepository) {
        this.nutritonHistoryRepository = nutritonHistoryRepository;
    }

    public void create (String login, String foodName){
        NutritionHistory nutritionHistory = new NutritionHistory();
        Long appUserId = appUserService.findUser(login).getId();
        Long foodId = foodService.findByName(foodName).getId();

        nutritionHistory.setMealTime(LocalDateTime.now());
        nutritionHistory.setAppUserId(appUserId);
        nutritionHistory.setFoodId(foodId);
        nutritonHistoryRepository.save(nutritionHistory);
    }

//    public void createNutritionHistory(NutritionHistoryDto nutritionHistoryDto, String login) {
//        NutritionHistory nutritionHistory = mapTo(nutritionHistoryDto);
//        AppUser owner = findUser(login);
//        nutritionHistory.setUser(owner);
//        nutritonHistoryRepository.save(nutritionHistory);
//    }
//
//
    public List<NutritionHistoryDto> findAllForUser(Long appUserId) {
        List<NutritionHistory> history = nutritonHistoryRepository.findAllByAppUserId(appUserId);
        return history.stream().map(this::mapTo).collect(Collectors.toList());
    }

        private NutritionHistory mapTo(NutritionHistoryDto nutritionHistoryDto) {
        NutritionHistory nutritionHistory = new NutritionHistory();
        nutritionHistory.setAppUserId(nutritionHistory.getAppUserId());
        nutritionHistory.setFoodId(nutritionHistory.getFoodId());
        nutritionHistory.setMealTime(nutritionHistoryDto.getMealTime());
        return nutritionHistory;
    }

    private NutritionHistoryDto mapTo(NutritionHistory nutritionHistory) {
        NutritionHistoryDto nutritionHistoryDto = new NutritionHistoryDto();
        nutritionHistory.setAppUserId(nutritionHistory.getAppUserId());
        nutritionHistory.setFoodId(nutritionHistory.getFoodId());
        nutritionHistory.setMealTime(nutritionHistoryDto.getMealTime());
        return nutritionHistoryDto;
    }



//
//    public NutritionHistoryDto findOneMeal(Long id) {
//        NutritionHistory nutritionHistory = nutritonHistoryRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));
//        return mapTo(nutritionHistory);
//    }
//
//    public void update(Long id, NutritionHistoryDto nutritionHistoryDto) {
//        NutritionHistory nutritionHistory = nutritonHistoryRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));
//        nutritionHistory.setMealTime(nutritionHistoryDto.getMealTime());
//        nutritonHistoryRepository.save(nutritionHistory);
//    }
//
//    private AppUser findUser(String login) {
//        return appUserRepository.findByLogin(login)
//                .orElseThrow(() -> new EntityNotFoundException("AppUser" + login + " not found."));
//
//    }
//
//    private NutritionHistory mapTo(NutritionHistoryDto nutritionHistoryDto) {
//        NutritionHistory nutritionHistory = new NutritionHistory();
//        nutritionHistory.setUser(nutritionHistory.getUser());
//        nutritionHistory.setFood(nutritionHistory.getFood());
//        nutritionHistory.setMealTime(nutritionHistoryDto.getMealTime());
//        return nutritionHistory;
//    }
//
//    private NutritionHistoryDto mapTo(NutritionHistory nutritionHistory) {
//        NutritionHistoryDto nutritionHistoryDto = new NutritionHistoryDto();
//        nutritionHistory.setUser(nutritionHistory.getUser());
//        nutritionHistory.setFood(nutritionHistory.getFood());
//        nutritionHistoryDto.setMealTime(nutritionHistory.getMealTime());
//        return nutritionHistoryDto;
//    }

}


