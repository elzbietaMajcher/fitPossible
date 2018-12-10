package pl.sda.fitpossible.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    private Date date;
    private Integer weight;
    @ManyToOne
    private AppUser user;

}
