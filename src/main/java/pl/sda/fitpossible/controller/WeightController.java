package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.fitpossible.dto.AddUserWeight;
import pl.sda.fitpossible.dto.WeightDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Weight;
import pl.sda.fitpossible.service.UserAuthenticationService;
import pl.sda.fitpossible.service.WeightService;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class WeightController {

    @Autowired
    public UserAuthenticationService userAuthenticationService;

    @Autowired
    public WeightService weightService;

    @GetMapping(path = "/weight")
    public String getWeightPage(Model model) {
        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
        if (appUserOptional.isPresent()) {

            String login = appUserOptional.get().getLogin();



            List<WeightDto> weightList = weightService.getWeightHistory(login);
            model.addAttribute("postWeight", new WeightDto().getClass());
            model.addAttribute("weightList", weightList);


            return "user/weight";
        }
        return "redirect:/login";
    }

    @PostMapping("/weight")
    public String addWeight(Model model, WeightDto dto) throws ParseException {
        Optional<AppUser> appUserOptional = userAuthenticationService.getLoggedInUser();
        if (appUserOptional.isPresent()) {

            String login = appUserOptional.get().getLogin();

            weightService.addWeight(dto,login);
            model.addAttribute("postWeight", dto);
            model.addAttribute("weightList", dto. getDate());

            return "redirect:/user/weight";
        }

        return "redirect:/login";
    }
}



















































