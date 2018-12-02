package pl.sda.fitpossible.dto;

import lombok.Data;

@Data
public class ActivityHistoryDto {
    private Long id;
    private String activityType;
    private int caloriesPerHour;
    private int caloriesPerRep;

}
