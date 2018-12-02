package pl.sda.fitpossible.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.service.AppUserService;
import pl.sda.fitpossible.service.FoodService;

import javax.validation.Valid;

@Controller
public class UserWebController {

    private AppUserService appUserService;

    public UserWebController (AppUserService appUserService){this.appUserService = appUserService;}

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("formObject", new AppUserDto());

        return "registration";
    }


    @PostMapping ("/register")
    public void register (@Valid @ModelAttribute ("user") AppUserDto appUserDto){
        appUserService.create(appUserDto);

    }

}
