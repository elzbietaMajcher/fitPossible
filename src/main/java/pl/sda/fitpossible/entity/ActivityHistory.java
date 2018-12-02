package pl.sda.fitpossible.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "activity_history")
public class ActivityHistory  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activityType;
    private int caloriesPerHour;
    private int caloriesPerRep;

    @ManyToOne
    private AppUser user;
}
