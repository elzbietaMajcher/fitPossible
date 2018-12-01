package pl.sda.fitpossible.entity;

import lombok.Data;
import pl.sda.fitpossible.dto.AppUserDto;

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
    @JoinColumn(name = "owner_id")
    private AppUser owner;

}
