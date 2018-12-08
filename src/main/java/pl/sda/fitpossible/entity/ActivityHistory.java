package pl.sda.fitpossible.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "activity_history")
public class ActivityHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activityType;
    private int caloriesPerHour;
    private int caloriesPerRep;
    // @CreationTimestamp
    private LocalDateTime startTime;
    //@UpdateTimestamp
    private LocalDateTime finishTime;
    private int reps;
    @ManyToOne
    private AppUser user;
}
