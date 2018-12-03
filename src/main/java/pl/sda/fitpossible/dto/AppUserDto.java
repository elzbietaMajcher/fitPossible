package pl.sda.fitpossible.dto;

import lombok.Data;
import pl.sda.fitpossible.entity.Gender;
import pl.sda.fitpossible.entity.LifestyleType;
import pl.sda.fitpossible.entity.UserRole;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
public class AppUserDto {

    @NotBlank
    private String login;
    @NotBlank
    private String password;

}
