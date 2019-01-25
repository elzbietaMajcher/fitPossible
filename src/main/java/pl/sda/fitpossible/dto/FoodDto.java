package pl.sda.fitpossible.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
public class FoodDto {

    private Long id;
    private String name;
    private Integer caloriesPerUnit;
    private String unit;
    private LocalTime time;
}
