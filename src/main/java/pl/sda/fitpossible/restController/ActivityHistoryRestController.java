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

    @PostMapping(value = "/add")
    public void addActivityToHistory(@RequestBody ActivityHistoryDto activityHistoryDto) {
        activityHistoryService.addActivity(activityHistoryDto);
    }

    @PostMapping(value = "/start")
    public void startActivity(@RequestBody ActivityHistoryDto activityHistoryDto) {
        activityHistoryService.startActivity(activityHistoryDto);
    }

    @PutMapping(value = "/finish/{id}")
    public void finishActivity(@PathVariable Long id) {
        activityHistoryService.stopActivity(id);
    }
}
