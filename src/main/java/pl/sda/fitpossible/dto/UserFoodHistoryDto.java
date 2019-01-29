package pl.sda.fitpossible.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class UserFoodHistoryDto {

    private LocalTime mealTime;
    private Long historyId;// id nutritionHistory
    private Long appUserId;

    private Long foodId;
    private String foodName;
    private Integer caloriesPerUnit;
    private String unit;


}
