package pl.sda.fitpossible.dto;

import lombok.Data;
import pl.sda.fitpossible.entity.AppUser;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data

public class WeightDto {


    private Long id;
    @NotNull
    private Date date;
    @NotNull
    private Integer weight;
    @NotNull
    private Long ownerId;
}
