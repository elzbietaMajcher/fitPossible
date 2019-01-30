package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.fitpossible.dto.ActivityDto;
import pl.sda.fitpossible.dto.ActivityHistoryDto;
import pl.sda.fitpossible.dto.ActivityHistoryRequest;
import pl.sda.fitpossible.entity.Activity;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.service.ActivityHistoryService;
import pl.sda.fitpossible.service.ActivityService;
import pl.sda.fitpossible.service.AppUserService;
import pl.sda.fitpossible.service.UserAuthenticationService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class ActivityWebController {

    @Autowired
    public UserAuthenticationService userAuthenticationService;

    @Autowired
    public AppUserService appUserService;

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityHistoryService activityHistoryService;

    @GetMapping(path = "/userActivity")
    public String getUserActivityPanel(Model model, Long deleteActivityId) {
        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
        if (appUserOptional.isPresent()){
            Long userId = appUserOptional.get().getId();

            if (deleteActivityId != null){
                activityHistoryService.deleteFromUserHistory(deleteActivityId);
                return "redirect:/user/userActivity";
            }
            List<ActivityDto> activityList = activityService.findAll();
           //List<ActivityDto> userDayActivityHistory = activityHistoryService.showUserDayActivityHistory(userId);

            List<ActivityHistoryDto> userDayActivityHistory = activityHistoryService.showUserActivityHistory(userId);


            model.addAttribute("userDayActivityHistory", userDayActivityHistory);
            model.addAttribute("listActivites", activityList);
            model.addAttribute("selectedActivity", new ActivityHistoryRequest());


           return "user/userActivity";
        }
        return "redirect:/login";
    }


    @PostMapping (path = "/userActivity")
    public String selectActivityFromList (Model model, ActivityHistoryRequest request){
        Optional<AppUser> optionalAppUser = userAuthenticationService.getLoggedInUser();
        if (optionalAppUser.isPresent()){
            Long userId = optionalAppUser.get().getId();
            ActivityHistoryDto activityHistoryDto = new ActivityHistoryDto();
//zrobić dostosowanie daty, imput ma taki zapis [2019-01-30T17:47]
            activityHistoryService.create(userId, request);
            model.addAttribute("selectedActivity", request);


            return "redirect:/user/userActivity";
        }
        return "redirect:/login";
    }

}
