package pl.sda.fitpossible.service;

import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.ActivityDto;
import pl.sda.fitpossible.entity.Activity;
import pl.sda.fitpossible.repository.ActivityRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;
    public ActivityService  (ActivityRepository activityRepository){this.activityRepository = activityRepository;}

    public void create (ActivityDto activityDto){
        Activity activity = mapTo(activityDto);
        activityRepository.save(activity);
    }

    public ActivityDto findActivity (Long id){//??
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found."));
        return mapTo(activity);
    }

    public List<Activity> findAll (){ //??
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(this::mapTo).collect(Collectors.toList());
    }

    public void update (Long id/*, ActivityDto activityDto*/){
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found."));
        //activity= mapTo(activityDto);
        activityRepository.save(activity);
    }

    public void delete (Long id){
        activityRepository.deleteById(id);
    }

    private Activity mapTo(ActivityDto activityDto){
        Activity activity = new Activity();
        activity.setId(activityDto.getId());
        activity.setActivityType(activityDto.getActivityType());
        activity.setCaloriesPerHour(activityDto.getCaloriesPerHour());
        activity.setCaloriesPerRep(activityDto.getCaloriesPerRep());
        return activity;
    }

    private ActivityDto mapTo(Activity activity){
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setActivityType(activity.getActivityType());
        activityDto.setCaloriesPerHour(activity.getCaloriesPerHour());
        activityDto.setCaloriesPerRep(activity.getCaloriesPerHour());
        return activityDto;
    }

}
