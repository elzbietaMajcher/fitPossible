package pl.sda.fitpossible.restController;

import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.ActivityDto;
import pl.sda.fitpossible.service.ActivityService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/activities")
public class ActivityRestController {

    private ActivityService activityService;

    public ActivityRestController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/create")  // ok
    public void crateActivity(@Valid @RequestBody ActivityDto activityDto) {
        activityService.create(activityDto);
    }

    @GetMapping("/find")  // ok
    public List<ActivityDto> findAllActivites() {
        return activityService.findAll();
    }

    @GetMapping("/find/{id}")  // ok
    public ActivityDto findOneActivity(@PathVariable Long id) {
        return activityService.findActivity(id);
    }

    @PutMapping("/update/{id}")  // ok
    public void updateActivity( @RequestBody ActivityDto activityDto, @PathVariable Long id) {
        activityService.updateActivity(id, activityDto);
    }

    @DeleteMapping("/delete/{id}")// ok
    public void deleteActivity(@PathVariable Long id) {
        activityService.delete(id);
    }

}
