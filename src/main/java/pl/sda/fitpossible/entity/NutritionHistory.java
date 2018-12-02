package pl.sda.fitpossible.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "nutrition_history")
public class NutritionHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer caloriesPerUnit;
    private String unit;

    @ManyToOne
    private AppUser user;
}
