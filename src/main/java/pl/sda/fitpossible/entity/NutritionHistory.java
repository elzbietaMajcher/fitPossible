package pl.sda.fitpossible.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "nutrition_history")
public class NutritionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer caloriesPerUnit;
    private String unit;
}
