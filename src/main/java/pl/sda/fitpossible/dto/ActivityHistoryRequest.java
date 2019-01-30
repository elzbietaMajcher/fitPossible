package pl.sda.fitpossible.dto;

import lombok.Data;
import pl.sda.fitpossible.entity.Activity;

import java.time.LocalDateTime;

@Data
public class ActivityHistoryRequest {

    private String activityName;

    private String startTime;
    private String finishTime;
    private int repeats;
}
