package pl.sda.fitpossible.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserWeight {
    private Long user_id;
    //@NotNull
    private Date date;
    //@NotNull
    private Integer weight;

    //TODO

}