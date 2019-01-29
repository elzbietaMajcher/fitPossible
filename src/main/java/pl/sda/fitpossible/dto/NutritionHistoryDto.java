package pl.sda.fitpossible.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class NutritionHistoryDto {

   // private Long id;

    private LocalDateTime mealTime;
    private Long historyId;

    @NotNull
    private Long appUserId;
    @NotNull
    private Long foodId;


}
