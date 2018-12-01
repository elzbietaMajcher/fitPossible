package pl.sda.fitpossible.dto;

import lombok.Data;
import pl.sda.fitpossible.entity.ActivityType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Data
public class ActivityDto {

    private Long id;
    @NotBlank
    private String activityType;
    @NotNull
    private int caloriesPerHour;
    @NotNull
    private int caloriesPerRep;
}
