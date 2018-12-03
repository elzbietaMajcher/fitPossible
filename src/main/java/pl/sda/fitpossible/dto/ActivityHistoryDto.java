package pl.sda.fitpossible.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ActivityHistoryDto {
    private Long user_id;
    @NotNull
    private String activityType;
    private int caloriesPerHour;
    private int caloriesPerRep;
    @CreationTimestamp
    private LocalDateTime startTime;
    @UpdateTimestamp
    private LocalDateTime finishTime;
    private int reps;

}
