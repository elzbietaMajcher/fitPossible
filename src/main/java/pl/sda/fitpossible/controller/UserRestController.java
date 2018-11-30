package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.UserDto;
import pl.sda.fitpossible.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void createUser(@Valid @RequestBody UserDto dto) {
        userService.create(dto);
        }

    @GetMapping("/{id}")
    public UserDto getUserById (@PathVariable long id){
        return userService.findUser(id);
    }


    @GetMapping("/all")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/delete/{id}")  //  zwracanie listy pozostałych?
    public void deleteUser (@PathVariable long id){
        userService.delete(id);

    }


}
