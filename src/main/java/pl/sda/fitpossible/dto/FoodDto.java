package pl.sda.fitpossible.dto;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

@Data
public class FoodDto {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @NumberFormat
    private Integer caloriesPerUnit;
    @NotBlank
    private String unit;
}
