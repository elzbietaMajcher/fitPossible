package pl.sda.fitpossible.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import pl.sda.fitpossible.dto.WeightDto;
        import pl.sda.fitpossible.service.WeightService;

        import javax.validation.Valid;
        import java.util.List;

@RestController
@RequestMapping(value = "/weight")
public class WeightRestController {

    @Autowired
    private WeightService weightService;

    public WeightRestController(WeightService weightService) {
        this.weightService = weightService;
    }

    @PostMapping("/add")
    public void addWeight(@Valid @RequestBody WeightDto weightDto) {
        weightService.addWeight(weightDto);

    }
    /*@PostMapping("/addWeight/{id}")
    public void addWeightForUser(@Valid @RequestBody WeightDto weightDto, Long id) {
        weightDto.setOwner(appUserDto.getId());
        weightService.addWeight(weightDto);

    }*/

    @GetMapping("/all/{id}")
    public List<WeightDto> allWeightByOwner(@PathVariable Long id) {
        return weightService.getWeightHistory();

    }
}
