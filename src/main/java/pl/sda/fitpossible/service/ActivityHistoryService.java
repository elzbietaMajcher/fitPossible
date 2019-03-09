package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.ActivityDto;
import pl.sda.fitpossible.dto.ActivityHistoryDto;
import pl.sda.fitpossible.dto.ActivityHistoryRequest;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.repository.ActivityHistoryRepository;
import pl.sda.fitpossible.repository.AppUserRepository;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ActivityService activityService;


    public void addActivity(ActivityHistoryDto activityHistoryDto, String login) {
        ActivityHistory activityHistory = mapTo(activityHistoryDto);
//        AppUser owner = findUser(login);
        //activityHistory.setUser(owner);
        activityHistoryRepository.save(activityHistory);
    }

    public void startActivity(ActivityHistoryDto activityHistoryDto, String login) {
        ActivityHistory activityHistory = mapTo(activityHistoryDto);
//        AppUser owner = findUser(login);
//        activityHistory.setUser(owner);
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
        List<ActivityHistory> activityHistories = activityHistoryRepository.findAllByUserLogin(login);
        return activityHistories.stream().map(this::mapTo).collect(Collectors.toList());
    }

    private AppUser findUser(String login) {
        return appUserRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("AppUser" + login + " not found."));

    }

    private ActivityHistoryDto mapTo(ActivityHistory activityHistory) {
        ActivityHistoryDto activityHistoryDto = new ActivityHistoryDto();
        activityHistoryDto.setId(activityHistory.getId());
        activityHistoryDto.setAppUserDto(appUserService.findUser(activityHistory.getUser().getId()));
        activityHistoryDto.setStartTime(activityHistory.getStartTime());
        activityHistoryDto.setFinishTime(activityHistory.getFinishTime());
        activityHistoryDto.setRepeats(activityHistory.getRepeats());
        activityHistoryDto.setActivityDto(activityService.findActivity(activityHistory.getActivity().getId()));
        return activityHistoryDto;
    }

    private ActivityHistory mapTo(ActivityHistoryDto activityHistoryDto) {
        ActivityHistory activityHistory = new ActivityHistory();
        activityHistory.setId(activityHistoryDto.getId());
        activityHistory.setUser(appUserService.mapTo(activityHistoryDto.getAppUserDto()));
        activityHistory.setStartTime(activityHistoryDto.getStartTime());
        activityHistory.setFinishTime(activityHistoryDto.getFinishTime());
        activityHistory.setRepeats(activityHistoryDto.getRepeats());
        activityHistory.setActivity(activityService.mapTo(activityHistoryDto.getActivityDto()));
        return activityHistory;
    }


    public void deleteFromUserHistory(Long deleteActivityId) {
        activityHistoryRepository.deleteById(deleteActivityId);
    }


    public void create(Long userId, ActivityHistoryRequest request) {
        ActivityHistory activityUserHistory = new ActivityHistory();
        String startTime = request.getStartTime();
        String finishTime = request.getStartTime();

        LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);

        LocalDateTime finish = LocalDateTime.parse(finishTime, DateTimeFormatter.ISO_DATE_TIME);
        int repeats = request.getRepeats();

        ActivityDto activityDto = activityService.findActivityByName(request.getActivityName());

        activityUserHistory.setActivity(activityService.mapTo(activityDto));
        activityUserHistory.setUser(appUserService.mapTo(appUserService.findUser(userId)));
        activityUserHistory.setRepeats(repeats);
        activityUserHistory.setStartTime(start);
        activityUserHistory.setFinishTime(finish);

        activityHistoryRepository.save(activityUserHistory);
    }

    public List<ActivityHistoryDto> showUserActivityHistory(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = now.toLocalDate();
        LocalTime time = LocalTime.parse("00:00:00");
        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        List<ActivityHistory> uahDto = activityHistoryRepository.findAllByUserIdAndStartTimeAfter(userId, localDateTime);

        return uahDto.stream().map(this::mapTo).collect(Collectors.toList());
    }

//    public void create(Long userId, ActivityHistoryDto request) {
//        ActivityHistory activityUserHistory = new ActivityHistory();
//
//        LocalDateTime start = putStartTime(request);
//        LocalDateTime finish = putFinishTime(request);
//        int repeats = putRepeats(request);
//
//        activityUserHistory.setActivity(activityService.mapTo(request.getActivityDto()));
//        activityUserHistory.setUser(appUserService.mapTo(appUserService.findUser(userId)));
//        activityUserHistory.setRepeats(repeats);
//        activityUserHistory.setStartTime(start);
//        activityUserHistory.setFinishTime(finish);
//
//        activityHistoryRepository.save(activityUserHistory);
//    }

//    private LocalDateTime putStartTime(ActivityHistoryDto request) {
//        if (request.getStartTime() != null) {
//            LocalDateTime localDateTime = request.getStartTime();
//            return localDateTime;
//        }
//        return LocalDateTime.now();
//    }
//
//    private LocalDateTime putFinishTime(ActivityHistoryDto request) {
//        if (request.getFinishTime() != null) {
//            LocalDateTime localDateTime = request.getFinishTime();
//            return localDateTime;
//        }
//        return LocalDateTime.now();
//    }
//
//    private int putRepeats(ActivityHistoryDto request) {
//        if (request.getRepeats() != 0) {
//            return request.getRepeats();
//        }
//        return 0;
//    }
}
