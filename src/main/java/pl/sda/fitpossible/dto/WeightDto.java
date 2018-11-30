package pl.sda.fitpossible.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class WeightDto {

    @NotEmpty
    private Long id;
    @NotEmpty
    private Date date;
    @NotEmpty
    private Integer weight;

}
