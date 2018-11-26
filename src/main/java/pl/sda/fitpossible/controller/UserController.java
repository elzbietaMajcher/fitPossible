package pl.sda.fitpossible.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.fitpossible.dto.UserDto;
import pl.sda.fitpossible.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void create(UserDto dto) {
        userService.create(dto);
    }

    /*@GetMapping
    public void*/
}
