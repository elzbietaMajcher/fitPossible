package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.dto.NutritionHistoryDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Food;
import pl.sda.fitpossible.entity.NutritionHistory;
import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.repository.NutritionHistoryRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionHistoryService {
    @Autowired
    private AppUserRepository appUserRepository;
    private NutritionHistoryRepository nutritonHistoryRepository;
    @Autowired
    private FoodService foodService;
    @Autowired
    private AppUserService appUserService;

    public NutritionHistoryService(NutritionHistoryRepository nutritonHistoryRepository) {
        this.nutritonHistoryRepository = nutritonHistoryRepository;
    }


    public List<NutritionHistoryDto> getUserAllNutritionHistory(Long id) {
        List<NutritionHistory> nutritionHistory = nutritonHistoryRepository.findAllByUserId(id);
        return nutritionHistory.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<NutritionHistoryDto> getUserDailyNutritionHistory(Long id) {

        LocalDateTime now = LocalDateTime.now();
        LocalDate date = now.toLocalDate();
        LocalTime time = LocalTime.parse("00:00:00");
        LocalDateTime past = LocalDateTime.of(date, time);
        List<NutritionHistory> history = nutritonHistoryRepository.findNutritionHistoryByMealTimeAfterAndAndUserId(past, id);

        return history.stream().map(this::mapToDto).collect(Collectors.toList());
    }


    public void create(String login, String foodName) {
        NutritionHistory nutritionHistory = new NutritionHistory();
        Long appUserId = appUserService.findUserByLogin(login).getId();
        Long food = foodService.findByName(foodName).getId();

        nutritionHistory.setMealTime(LocalDateTime.now());
        AppUser owner = findUser(login);
        nutritionHistory.setUser(owner);
        nutritionHistory.setFoodId(food);
        nutritonHistoryRepository.save(nutritionHistory);
    }

    private AppUser findUser(String login) { //TODO some classes use exactly the same method
        return appUserRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("AppUser" + login + " not found."));

    }


    private NutritionHistory mapTo(NutritionHistoryDto nutritionHistoryDto) {//TODO is not used, do we need this?
        NutritionHistory nutritionHistory = new NutritionHistory();
        nutritionHistory.setFoodId(nutritionHistory.getFoodId());
        nutritionHistory.setMealTime(nutritionHistoryDto.getMealTime());
        nutritionHistory.setId(nutritionHistoryDto.getHistoryId());
        return nutritionHistory;
    }

    private NutritionHistoryDto mapToDto(NutritionHistory nutritionHistory) {
        NutritionHistoryDto nutritionHistoryDto = new NutritionHistoryDto();
        nutritionHistoryDto.setAppUserId(nutritionHistory.getUser().getId());
        nutritionHistoryDto.setFoodId(nutritionHistory.getFoodId());
        nutritionHistoryDto.setMealTime(nutritionHistory.getMealTime());
        nutritionHistoryDto.setHistoryId(nutritionHistory.getId());
        return nutritionHistoryDto;
    }

    public void deleteFromUserHistory(Long deleteId) {
        nutritonHistoryRepository.deleteById(deleteId);
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


