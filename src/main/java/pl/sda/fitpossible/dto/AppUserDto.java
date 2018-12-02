package pl.sda.fitpossible.dto;

import lombok.Data;
import pl.sda.fitpossible.entity.Gender;
import pl.sda.fitpossible.entity.LifestyleType;
import pl.sda.fitpossible.entity.Weight;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AppUserDto {

    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @Email
    private String email;

    private Gender gender;

    private Integer height;

    private Date dateOfBirth;

    private LifestyleType lifestyle;


}
