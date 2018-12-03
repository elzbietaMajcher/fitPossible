package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.ActivityHistoryDto;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.repository.ActivityHistoryRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ActivityHistoryService {


    private ActivityHistoryRepository activityHistoryRepository;

    @Autowired
    public ActivityHistoryService(ActivityHistoryRepository activityHistoryRepository) {
        this.activityHistoryRepository = activityHistoryRepository;
    }

    public void addActivity(ActivityHistoryDto activityHistoryDto) {
        ActivityHistory activityHistory = mapTo(activityHistoryDto);
        activityHistoryRepository.save(activityHistory);
    }

    public void startActivity(ActivityHistoryDto activityHistoryDto){
        ActivityHistory activityHistory = mapTo(activityHistoryDto);
        activityHistory.setStartTime(LocalDateTime.now());
        activityHistoryRepository.save(activityHistory);
    }

    public void stopActivity(Long id) {
        Optional<ActivityHistory> activity = activityHistoryRepository.findById(id);
        ActivityHistory currentActivity = activity.orElseThrow(() -> new EntityNotFoundException("Activity not found " + id));
        currentActivity.setFinishTime(LocalDateTime.now());
        activityHistoryRepository.save(currentActivity);
    }

    private ActivityHistoryDto mapTo(ActivityHistory activityHistory) {
        ActivityHistoryDto activityHistoryDto = new ActivityHistoryDto();
        activityHistoryDto.setActivityType(activityHistory.getActivityType());
        activityHistoryDto.setCaloriesPerHour(activityHistory.getCaloriesPerHour());
        activityHistoryDto.setCaloriesPerRep(activityHistory.getCaloriesPerRep());
        //activityHistoryDto.setTime(activityHistory.getTime());
        activityHistoryDto.setReps(activityHistory.getReps());
        return activityHistoryDto;
    }

    private ActivityHistory mapTo(ActivityHistoryDto activityHistoryDto) {
        ActivityHistory activityHistory = new ActivityHistory();
        activityHistory.setActivityType(activityHistoryDto.getActivityType());
        activityHistory.setCaloriesPerHour(activityHistoryDto.getCaloriesPerHour());
        activityHistory.setCaloriesPerRep(activityHistoryDto.getCaloriesPerRep());
        //activityHistory.setTime(activityHistoryDto.getTime());
        activityHistory.setReps(activityHistoryDto.getReps());
        return activityHistory;
    }
}
