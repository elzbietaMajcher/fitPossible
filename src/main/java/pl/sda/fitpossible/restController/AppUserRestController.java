package pl.sda.fitpossible.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.service.AppUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class AppUserRestController {

    @Autowired
    private AppUserService appUserService;

    public AppUserRestController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/create") //ok
    public void createUser(@Valid @RequestBody AppUserDto dto) {
        appUserService.create(dto);
    }

    @PutMapping("/update/{login}") // ok
    public void updateUser(@RequestBody AppUserDto dto, @PathVariable String login) {
        appUserService.findUser(login);
        appUserService.update(login, dto);
    }

    @GetMapping("/{id}") //??
    public void getUserById(@PathVariable long id) {
        appUserService.findUser(id);
    }

    @GetMapping("/{login}")  //??
    public void getUserByLogin(@PathVariable String login) {
        appUserService.findUser(login);
    }

    @GetMapping("/all")//ok
    public void findAll() {
        appUserService.findAll();
    }

    @GetMapping(value = "/delete/{login}")  //??
    public void deleteUser(@PathVariable String login) {
        appUserService.delete(login);

    }


}
