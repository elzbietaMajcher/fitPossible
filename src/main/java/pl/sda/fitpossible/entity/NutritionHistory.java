package pl.sda.fitpossible.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "nutrition_history")
public class NutritionHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime mealTime;
    private Long foodId;

    @ManyToOne
    private AppUser user;

//TODO: possibility to add quantity / pieces of food, example: sandwich 2 pieces
//
//
//
//
//    @ManyToOne
//    private Food food;
//
//    //@CreationTimestamp
//   // @UpdateTimestamp
//    private LocalDateTime mealTime;
//
//    @ManyToOne
//    private AppUser user;
}
