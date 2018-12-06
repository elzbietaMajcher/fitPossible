package pl.sda.fitpossible.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.ActivityHistoryDto;
import pl.sda.fitpossible.service.ActivityHistoryService;

@RestController
@RequestMapping(value = "/activity_history")
public class ActivityHistoryRestController {
    @Autowired
    private ActivityHistoryService activityHistoryService;

    public ActivityHistoryRestController(ActivityHistoryService activityHistoryService) {
        this.activityHistoryService = activityHistoryService;
    }

    @PostMapping(value = "/add/{login}")//
    public void addActivityToHistory(@RequestBody ActivityHistoryDto activityHistoryDto, @PathVariable String login) {
        activityHistoryService.addActivity(activityHistoryDto, login);
    }

    @PostMapping(value = "/start/{login}") //ok
    public void startActivity(@RequestBody ActivityHistoryDto activityHistoryDto,  @PathVariable String login) {
        activityHistoryService.startActivity(activityHistoryDto, login);
    }

    @PutMapping(value = "/finish/{id}")  //ok
    public void finishActivity(@PathVariable Long id) {
        activityHistoryService.stopActivity(id);
    }
}
