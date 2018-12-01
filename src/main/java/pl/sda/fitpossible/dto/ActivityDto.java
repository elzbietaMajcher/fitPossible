package pl.sda.fitpossible.dto;

import lombok.Data;
import pl.sda.fitpossible.entity.ActivityType;
import javax.validation.constraints.NotBlank;


@Data
public class ActivityDto {

    private Long id;
    @NotBlank
    private String activityType;

    private int caloriesPerHour;

    private int caloriesPerRep;
}
