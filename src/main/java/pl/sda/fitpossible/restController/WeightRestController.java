package pl.sda.fitpossible.restController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.fitpossible.dto.WeightDto;
import pl.sda.fitpossible.service.WeightService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/weight")
public class WeightRestController {

    /*@Autowired*/
    private WeightService weightService;

    public WeightRestController(WeightService weightService) {
        this.weightService = weightService;
    }

    @PostMapping("/add")
    public void addWeight(@Valid @RequestBody WeightDto weightDto) {
        weightService.add(weightDto);
    }
}
