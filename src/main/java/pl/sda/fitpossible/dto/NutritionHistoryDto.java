package pl.sda.fitpossible.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class NutritionHistoryDto {

    private Long user_id;
    @NotBlank
    private String name;
    @NotNull
    private Integer caloriesPerUnit;
    @NotBlank
    private String unit;
    @NotNull
    private LocalDateTime mealTime;


}
