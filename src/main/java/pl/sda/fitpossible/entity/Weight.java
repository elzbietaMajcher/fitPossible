package pl.sda.fitpossible.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

@Data
@Entity
@Table(name = "weight")
public class Weight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Integer weight;
    @ManyToOne
    private AppUser user;

}
