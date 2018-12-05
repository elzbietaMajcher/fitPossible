package pl.sda.fitpossible.restController;

import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.ActivityDto;
import pl.sda.fitpossible.service.ActivityService;

import javax.validation.Valid;

@RestController
@RequestMapping (value = "/activities")
public class ActivityRestController {

    private ActivityService activityService;
    public ActivityRestController(ActivityService activityService) {
        this.activityService = activityService;}

    @PostMapping ("/create")
    public void crateActivity (@Valid @RequestBody ActivityDto activityDto){
        activityService.create(activityDto);
    }

    @GetMapping ("/find")
    public void findAllActivites (){
        activityService.findAll();
    }

    @GetMapping ("/find/{id}")
    public void findOneActivity (@RequestParam Long id){
        activityService.findActivity(id);
    }

    @PutMapping ("/update/{id}")  // ??
    public void updateActivity(/*@Valid @RequestBody ActivityDto activityDto,*/ @RequestParam Long id){
        activityService.update(id/*, activityDto*/);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteActivity (@RequestParam Long id){
        activityService.delete(id);
    }

}
