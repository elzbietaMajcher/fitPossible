package pl.sda.fitpossible.entity;
/*id, login, password, sex, hight, birthDate, status
-supDATABASE : with history , id, dete, weihgt
-id dete, status of activity, goals*/
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.security.AuthProvider;
import java.util.Date;
import java.util.Set;

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

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Collection<Weight> weightMeasurements;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Collection<NutritionHistory> userNutritionHistories;




//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private Collection<NutritionHistory> nutritionHistory ;
//
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private Collection<ActivityHistory> activityHistory;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles;


}
