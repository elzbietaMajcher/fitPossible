package pl.sda.fitpossible.entity;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @NumberFormat
    private Integer caloriesPerUnit;
    @NotBlank
    private String unit;
}
