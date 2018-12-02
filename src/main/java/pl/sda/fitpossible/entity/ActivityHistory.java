package pl.sda.fitpossible.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "activity_history")
public class ActivityHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activityType;
    private int caloriesPerHour;
    private int caloriesPerRep;




}
