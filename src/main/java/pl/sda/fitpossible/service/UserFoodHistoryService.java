package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.dto.NutritionHistoryDto;
import pl.sda.fitpossible.dto.UserFoodHistoryDto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserFoodHistoryService {

    @Autowired
    public NutritionHistoryService nutritionHistoryService;

    @Autowired
    public AppUserService appUserService;

    @Autowired
    public FoodService foodService;


    public List<UserFoodHistoryDto> showUserFoodHistory(Long userId) {
        List<UserFoodHistoryDto> foodHistory = new ArrayList<>();
        List<NutritionHistoryDto> nutritionHistoryDtos = nutritionHistoryService.getUserDailyNutritionHistory(userId);
        for (NutritionHistoryDto n : nutritionHistoryDtos) {
            UserFoodHistoryDto ufhDto = mapTo(n);
            foodHistory.add(ufhDto);
        }
        return foodHistory;
    }

    private UserFoodHistoryDto mapTo(NutritionHistoryDto n) {
        UserFoodHistoryDto ufhDto = new UserFoodHistoryDto();
        FoodDto foodDto = foodService.findFood(n.getFoodId());
        ufhDto.setAppUserId(n.getAppUserId());
        ufhDto.setFoodId(n.getFoodId());
        LocalTime time = LocalTime.from(n.getMealTime());
        ufhDto.setMealTime(time);
        ufhDto.setHistoryId(n.getHistoryId());

        ufhDto.setFoodName(foodDto.getName());
        ufhDto.setCaloriesPerUnit(foodDto.getCaloriesPerUnit());
        ufhDto.setUnit(foodDto.getUnit());

        return ufhDto;
    }

}