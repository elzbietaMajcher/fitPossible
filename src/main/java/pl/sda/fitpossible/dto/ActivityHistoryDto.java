package pl.sda.fitpossible.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ActivityHistoryDto {

//    private Long id;
//    private Long user_id;
//    private Long activityTypeId;
//    private LocalDateTime startTime;
//    private LocalDateTime finishTime;
//    private int repeats;

    private Long id;
    private AppUserDto appUserDto;
    private ActivityDto activityDto;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private int repeats;

}
