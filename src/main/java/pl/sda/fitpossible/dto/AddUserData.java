package pl.sda.fitpossible.dto;

import lombok.Data;
import pl.sda.fitpossible.entity.Gender;
import pl.sda.fitpossible.entity.LifestyleType;

import java.util.Date;

@Data
public class AddUserData {

    private String email;

    private Gender gender;

    private Integer height;

    private Date dateOfBirth;

    private LifestyleType lifestyle;
}
