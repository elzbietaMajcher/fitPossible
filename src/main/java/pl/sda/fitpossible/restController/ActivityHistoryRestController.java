package pl.sda.fitpossible.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.fitpossible.dto.ActivityHistoryDto;
import pl.sda.fitpossible.service.ActivityHistoryService;

@RestController
@RequestMapping(value = "activity_history")
public class ActivityHistoryRestController {
    @Autowired
    private ActivityHistoryService activityHistoryService;

    public ActivityHistoryRestController(ActivityHistoryService activityHistoryService) {
        this.activityHistoryService = activityHistoryService;
    }

    @PostMapping(value = "add")
    public void addActivityHistory(@RequestBody ActivityHistoryDto activityHistoryDto) {
        activityHistoryService.addActivityHistory(activityHistoryDto);
    }
}
