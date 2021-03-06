package pl.sda.fitpossible.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data

public class WeightDto {

    private Long user_id;

    private Date date;
    //@NotNull
    private Integer weight;

}