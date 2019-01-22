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
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Food;
import pl.sda.fitpossible.entity.NutritionHistory;
import pl.sda.fitpossible.service.AppUserService;
import pl.sda.fitpossible.service.FoodService;
import pl.sda.fitpossible.service.NutritionHistoryService;
import pl.sda.fitpossible.service.UserAuthenticationService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class FoodController {
    @Autowired
    public UserAuthenticationService userAuthenticationService;

    @Autowired
    public AppUserService appUserService;

    @Autowired
    public FoodService foodService;

    @Autowired
    public NutritionHistoryService nutritionHistoryService;

    @GetMapping(path = "/userFood")
    public String getFoodList(Model model) {
        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
        if (appUserOptional.isPresent()) {
            List<FoodDto> listFood = foodService.findAll();
            Long id = appUserOptional.get().getId();
            List<NutritionHistoryDto> userDayFoodHistory = nutritionHistoryService.getUserDailyNutritionHistory(id);
//            List<NutritionHistoryDto> userDayFoodHistory = nutritionHistoryService.getUserNutritionHistory();

            model.addAttribute("userDayHistory", userDayFoodHistory);
            model.addAttribute("listFoods", listFood);
            model.addAttribute("selectedFood", new SelectedFoodDto());
            return "user/userFood";
        }
        return "redirect:/login";
    }


    @PostMapping(path = "/userFood")
    public String getUserFood(Model model, SelectedFoodDto request) {
        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
        if (appUserOptional.isPresent()) {

            String appUserName = appUserOptional.get().getLogin();

            //przerobic nutriton history dto, i metode w service ,
            //w update login, nutrition history z id food i id user + autogeneracja daty
            // FoodDto foodDto = request.getFoodDto();


            nutritionHistoryService.create(appUserName, request.getFoodDto());
            model.addAttribute("selectedFood", request);


            return "redirect:/user/userFood";
        }
        return "redirect:/login";
    }

//    @PostMapping(path = "/userFood")
//    public String postUserFood(Model model, String foodName) {
//        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
//        String appUserName = appUserOptional.get().getLogin();
//
//        //przerobic nutriton history dto, i metode w service ,
//        //w update login, nutrition history z id food i id user + autogeneracja daty
//        List<FoodDto> listFood = foodService.findAll();
//        model.addAttribute("listFood", listFood);
//        nutritionHistoryService.create(appUserName,foodName);
//        return "redirect:user/userFood";
//    }

//
//
//    @PostMapping(path = "/userFood")
//    public String postUserFood(Model model, FoodDto foodDto) {
//        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
//        Long appUserId = appUserOptional.get().getId();
//        Long foodId = foodDto.getId();
//        //przerobic nutriton history dto, i metode w service ,
//        //w update login, nutrition history z id food i id user + autogeneracja daty
//
//        nutritionHistoryService.create(appUserId, foodId);
//
//        model.addAttribute("chooseFromListFood", );
//
//
//        return "redirect:user/userFood";
//    }
}