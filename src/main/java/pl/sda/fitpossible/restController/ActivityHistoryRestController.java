package pl.sda.fitpossible.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.ActivityHistoryDto;
import pl.sda.fitpossible.repository.ActivityHistoryRepository;
import pl.sda.fitpossible.service.ActivityHistoryService;

import java.util.List;

@RestController
@RequestMapping(value = "/activity_history")
public class ActivityHistoryRestController {
    @Autowired
    private ActivityHistoryService activityHistoryService;

    public ActivityHistoryRestController(ActivityHistoryService activityHistoryService) {
        this.activityHistoryService = activityHistoryService;
    }

    @PostMapping(
            "/add/{login}")//ok
    public void addActivityToHistory(@RequestBody ActivityHistoryDto activityHistoryDto, @PathVariable String login) {
        activityHistoryService.addActivity(activityHistoryDto, login);
    }

    @PostMapping("/start/{login}") //ok
    public void startActivity(@RequestBody ActivityHistoryDto activityHistoryDto, @PathVariable String login) {
        activityHistoryService.startActivity(activityHistoryDto, login);
    }

    @PutMapping("/finish/{id}")  //ok
    public void finishActivity(@PathVariable Long id) {
        activityHistoryService.stopActivity(id);
    }

    @GetMapping("/find_all/{login}")  //ok
    public List<ActivityHistoryDto> getActivityHstoryByLogin(@PathVariable String login){
        return activityHistoryService.getActivityHistory(login);
    }
}
