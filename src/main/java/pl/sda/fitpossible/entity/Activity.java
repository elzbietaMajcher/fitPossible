package pl.sda.fitpossible.entity;
/*ACTIVITY DATABASE -entity to restController,
CRUD -adding activity to db - testing by Postman -API
-Data bases with 10 examples*/

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activityType;
    private int caloriesPerHour;
    private int caloriesPerRep;
}
