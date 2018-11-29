package pl.sda.fitpossible.dto;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

@Data
public class FoodDto {

    private Long id;

    private String name;

    private Integer caloriesPerUnit;

    private String unit;
}
