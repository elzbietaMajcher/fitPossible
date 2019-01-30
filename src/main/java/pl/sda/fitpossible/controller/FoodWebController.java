package pl.sda.fitpossible.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.dto.NutritionHistoryDto;
import pl.sda.fitpossible.dto.SelectedFoodDto;
import pl.sda.fitpossible.dto.UserFoodHistoryDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Food;
import pl.sda.fitpossible.entity.NutritionHistory;
import pl.sda.fitpossible.service.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class FoodWebController {
    @Autowired
    public UserAuthenticationService userAuthenticationService;

    @Autowired
    public AppUserService appUserService;

    @Autowired
    public FoodService foodService;

    @Autowired
    public UserFoodHistoryService userFoodHistoryService;

    @Autowired
    public NutritionHistoryService nutritionHistoryService;

    @GetMapping(path = "/userFood")
    public String getFoodList(Model model, Long deleteId) {
        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
        if (appUserOptional.isPresent()) {
            Long id = appUserOptional.get().getId();

            if (deleteId != null){
                nutritionHistoryService.deleteFromUserHistory(deleteId);
                return "redirect:/user/userFood";
            }

            List<FoodDto> listFood = foodService.findAll();

            List<UserFoodHistoryDto> userDayFoodHistory = userFoodHistoryService.showUserFoodHistory(id);

            int calculate = userFoodHistoryService.calculateCalories(id);

            model.addAttribute("userDayHistory", userDayFoodHistory);
            model.addAttribute("listFoods", listFood);
            model.addAttribute("selectedFood", new SelectedFoodDto());
            model.addAttribute("calculateCalories", calculate);
            return "user/userFood";
        }
        return "redirect:/login";
    }


    @PostMapping(path = "/userFood")
    public String getUserFood(Model model, SelectedFoodDto request) {
        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
        if (appUserOptional.isPresent()) {

            String appUserName = appUserOptional.get().getLogin();

            nutritionHistoryService.create(appUserName, request.getFoodDto());
            model.addAttribute("selectedFood", request);


            return "redirect:/user/userFood";
        }
        return "redirect:/login";
    }

}