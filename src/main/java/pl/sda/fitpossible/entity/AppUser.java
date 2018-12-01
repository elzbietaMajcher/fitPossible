package pl.sda.fitpossible.entity;
/*id, login, password, sex, hight, birthDate, status
-supDATABASE : with history , id, dete, weihgt
-id dete, status of activity, goals*/

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer height;
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private LifestyleType lifestyle;

    @OneToMany
    @JoinColumn(name = "owner_id")
    private Collection<Weight> weightMeasurements = new ArrayList<>();


}
