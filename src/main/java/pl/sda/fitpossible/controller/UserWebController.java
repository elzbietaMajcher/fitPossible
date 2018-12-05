package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.fitpossible.service.AppUserService;
import pl.sda.fitpossible.service.UserAuthenticationService;

@Controller
@RequestMapping (path = "/user")
public class UserWebController {

//    @Autowired
//    public UserAuthenticationService userAuthenticationService;
//
//    @Autowired
//    public AppUserService appUserService;
//
//    @GetMapping("/userPage")
//    public String getUserPage() {return "user/userPage";}
//

}
