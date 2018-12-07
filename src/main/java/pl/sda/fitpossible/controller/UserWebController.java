package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.fitpossible.dto.AddUserData;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.service.AppUserService;
import pl.sda.fitpossible.service.UserAuthenticationService;

import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UserWebController {

    @Autowired
    public UserAuthenticationService userAuthenticationService;

    @Autowired
    public AppUserService appUserService;


    @GetMapping("/userPage")
    public String getUserPage() {
        return "user/userPage";
    }

    @GetMapping(path = "/userForm")
    public String getUserForm(Model model) {
        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
        if (appUserOptional.isPresent()) {

            //zmodyfikowac aby nie wyświetlał hasła

            model.addAttribute("appUserDto",appUserOptional);
            return "user/userForm";
        }
        return "redirect:/login";
    }

//    @PutMapping("/userForm")
//    public String updateUserForm(Model model, AppUserDto appUserDto) {
//        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
//
//            String optionalLogin = appUserOptional.get().getLogin();
//
//
//            model.addAttribute("updateUserData",appUserOptional);
//            appUserService.update(optionalLogin, appUserDto);
//
//            return "user/userForm";
//
//    }




    @PutMapping("/userForm")
    public String updateUserForm(Model model, AppUserDto appUserDto) {
        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
        if (appUserOptional.isPresent()) {
            String optionalLogin = appUserOptional.get().getLogin();


        model.addAttribute("updateUserData",appUserOptional);
        appUserService.update(optionalLogin, appUserDto);

        return "user/userForm";
    }
        return "redirect:/login";
    }


//
//
//    @PostMapping("/userForm")
//    public String updateUserForm(Model model, AppUserDto appUserDto) {
//
//        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
//        if (appUserOptional.isPresent()) {
//            String login = appUserOptional.get().getLogin();
//
//
//
//            appUserService.updateByLogin(login, appUserDto);
//            model.addAttribute("updateUserData",appUserDto);
//
//            return "user/userForm";
//        }
//        return "redirect:/login";
//
//    }
}