package pl.sda.fitpossible.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.dto.WeightDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Weight;
import pl.sda.fitpossible.service.AppUserService;
import pl.sda.fitpossible.service.WeightService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/weight")
public class WeightRestController {

    @Autowired
    private WeightService weightService;
    @Autowired
    private AppUserService appUserService;


    public WeightRestController(WeightService weightService) {
        this.weightService = weightService;
    }

    @PostMapping("/add/{login}")
    public void addWeight(@Valid @RequestBody WeightDto weightDto, @PathVariable String login) {
        AppUser owner = appUserService.findUser(login);
        weightService.addWeight(weightDto,owner);
    }

    @GetMapping("/find_weight/{login}")
    public List<Weight> getWeightHistoryByLogin(/*AppUserDto dto,*/ @PathVariable String login) {
        AppUser owner = appUserService.findUser(login);
        return weightService.getWeightHistory(owner);
    }
}

