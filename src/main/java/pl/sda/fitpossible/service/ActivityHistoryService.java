package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.ActivityHistoryDto;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.repository.ActivityHistoryRepository;
import pl.sda.fitpossible.repository.AppUserRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityHistoryService {


    private ActivityHistoryRepository activityHistoryRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    public ActivityHistoryService(ActivityHistoryRepository activityHistoryRepository) {
        this.activityHistoryRepository = activityHistoryRepository;
    }

    public void addActivity(ActivityHistoryDto activityHistoryDto, String login) {
        ActivityHistory activityHistory = mapTo(activityHistoryDto);
        AppUser owner = findUser(login);
        activityHistory.setUser(owner);
        activityHistoryRepository.save(activityHistory);
    }

    public void startActivity(ActivityHistoryDto activityHistoryDto,  String login) {
        ActivityHistory activityHistory = mapTo(activityHistoryDto);
        AppUser owner = findUser(login);
        activityHistory.setUser(owner);
        activityHistory.setStartTime(LocalDateTime.now());
        activityHistoryRepository.save(activityHistory);
    }

    public void stopActivity(Long id) {
        Optional<ActivityHistory> activity = activityHistoryRepository.findById(id);
        ActivityHistory currentActivity = activity.orElseThrow(() -> new EntityNotFoundException("Activity not found " + id));
        currentActivity.setFinishTime(LocalDateTime.now());
        activityHistoryRepository.save(currentActivity);
    }

    public List<ActivityHistoryDto> getActivityHistory(String login) {
        List<ActivityHistory> activityHistories=activityHistoryRepository.findAllByUserLogin(login);
        return activityHistories.stream().map(this::mapTo).collect(Collectors.toList());
    }

    private AppUser findUser(String login) { // jak wyniesc te metode aby ja uwspolnic
        return appUserRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("AppUser" + login + " not found."));

    }

    private ActivityHistoryDto mapTo(ActivityHistory activityHistory) {
        ActivityHistoryDto activityHistoryDto = new ActivityHistoryDto();
        activityHistoryDto.setActivityType(activityHistory.getActivityType());
        activityHistoryDto.setCaloriesPerHour(activityHistory.getCaloriesPerHour());
        activityHistoryDto.setCaloriesPerRep(activityHistory.getCaloriesPerRep());
        activityHistoryDto.setStartTime(activityHistory.getStartTime());
        activityHistoryDto.setFinishTime(activityHistory.getFinishTime());
        activityHistoryDto.setReps(activityHistory.getReps());
        return activityHistoryDto;
    }

    private ActivityHistory mapTo(ActivityHistoryDto activityHistoryDto) {
        ActivityHistory activityHistory = new ActivityHistory();
        activityHistory.setActivityType(activityHistoryDto.getActivityType());
        activityHistory.setCaloriesPerHour(activityHistoryDto.getCaloriesPerHour());
        activityHistory.setCaloriesPerRep(activityHistoryDto.getCaloriesPerRep());
        activityHistory.setStartTime(activityHistoryDto.getStartTime());
        activityHistory.setFinishTime(activityHistoryDto.getFinishTime());
        activityHistory.setReps(activityHistoryDto.getReps());
        return activityHistory;
    }


}
