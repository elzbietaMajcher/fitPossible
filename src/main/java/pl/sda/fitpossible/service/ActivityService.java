package pl.sda.fitpossible.service;

import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.ActivityDto;
import pl.sda.fitpossible.entity.Activity;
import pl.sda.fitpossible.repository.ActivityRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void create(ActivityDto activityDto) {
        Activity activity = mapTo(activityDto);
        activityRepository.save(activity);
    }

    public ActivityDto findActivity(Long id) {//??
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found."));
        return mapTo(activity);
    }

    public List<ActivityDto> findAll() { //??
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(this::mapTo).collect(Collectors.toList());
    }

    public void updateActivity(Long id, ActivityDto activityDto) {
        Optional<Activity> activity = activityRepository.findById(id);
        Activity activityToUpdate = activity
                .orElseThrow(() -> new EntityNotFoundException("Activity not found."));
        activityToUpdate.setActivityType(activityDto.getActivityType());
        activityToUpdate.setCaloriesPerHour(activityDto.getCaloriesPerHour());
        activityToUpdate.setCaloriesPerRep(activityDto.getCaloriesPerRep());
        activityRepository.save(activityToUpdate);
    }

    public void delete(Long id) {
        activityRepository.deleteById(id);
    }

    protected Activity mapTo(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setId(activityDto.getId());
        activity.setActivityType(activityDto.getActivityType());
        activity.setCaloriesPerHour(activityDto.getCaloriesPerHour());
        activity.setCaloriesPerRep(activityDto.getCaloriesPerRep());
        return activity;
    }

    protected ActivityDto mapTo(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setActivityType(activity.getActivityType());
        activityDto.setCaloriesPerHour(activity.getCaloriesPerHour());
        activityDto.setCaloriesPerRep(activity.getCaloriesPerHour());
        return activityDto;
    }

    public ActivityDto findActivityByName(String activityName) {
        Activity activity = activityRepository.findActivityByActivityType(activityName)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found."));
        return mapTo(activity);
    }
}
