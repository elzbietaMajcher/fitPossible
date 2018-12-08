package pl.sda.fitpossible.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.fitpossible.entity.Gender;
import pl.sda.fitpossible.entity.LifestyleType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserData {

    private String email;

    private Gender gender;

    private Integer height;

    private String dateOfBirth;

    private LifestyleType lifestyle;

}
