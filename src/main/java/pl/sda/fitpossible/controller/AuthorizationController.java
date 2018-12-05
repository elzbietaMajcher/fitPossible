package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.dto.AuthorizationUserData;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.service.AppUserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthorizationController {

    @Autowired
    public AppUserService appUserService;


    @GetMapping("/login")
    public String getLoginPage() {
        return "authorization/login";
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("formObject", new AppUserDto());
        return "authorization/register";
    }

    @PostMapping("/register")
    public String register(Model model, AuthorizationUserData authorizationUserData) {

        Optional<AppUser> appUserOptional = appUserService.registry(authorizationUserData);
        if (appUserOptional.isPresent()) {
            return "redirect:/login";
        }

        model.addAttribute("message", "Could not register!");
        model.addAttribute("formObject", authorizationUserData);
        return "authorization/register";
    }
}
