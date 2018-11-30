package pl.sda.fitpossible.dto;

import lombok.Data;
import pl.sda.fitpossible.entity.Gender;
import pl.sda.fitpossible.entity.LifestyleType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class UserDto {

    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private Integer height;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private LifestyleType lifestyle;

}
