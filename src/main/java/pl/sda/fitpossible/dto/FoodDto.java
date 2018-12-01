package pl.sda.fitpossible.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FoodDto {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Integer caloriesPerUnit;
    @NotBlank
    private String unit;
}
