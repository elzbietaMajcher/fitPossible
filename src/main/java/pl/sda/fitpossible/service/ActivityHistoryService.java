package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.ActivityHistoryDto;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.repository.ActivityHistoryRepository;

@Service
public class ActivityHistoryService {


    private ActivityHistoryRepository activityHistoryRepository;

    @Autowired

    public ActivityHistoryService(ActivityHistoryRepository activityHistoryRepository) {
        this.activityHistoryRepository = activityHistoryRepository;
    }

    public void addActivityHistory(ActivityHistoryDto activityHistoryDto) {
        ActivityHistory activityHistory =mapTo(activityHistoryDto);
        activityHistoryRepository.save(activityHistory);
    }

    private ActivityHistoryDto mapTo(ActivityHistory activityHistory) {
        ActivityHistoryDto activityHistoryDto = new ActivityHistoryDto();
        activityHistoryDto.setActivityType(activityHistory.getActivityType());
        activityHistoryDto.setCaloriesPerHour(activityHistory.getCaloriesPerHour());
        activityHistoryDto.setCaloriesPerRep(activityHistory.getCaloriesPerRep());
        return activityHistoryDto;
    }

    private ActivityHistory mapTo(ActivityHistoryDto activityHistoryDto) {
        ActivityHistory activityHistory = new ActivityHistory();
        activityHistory.setActivityType(activityHistoryDto.getActivityType());
        activityHistory.setCaloriesPerHour(activityHistoryDto.getCaloriesPerHour());
        activityHistory.setCaloriesPerRep(activityHistoryDto.getCaloriesPerRep());
        return activityHistory;
    }
}
