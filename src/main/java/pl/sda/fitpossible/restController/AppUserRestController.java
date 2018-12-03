package pl.sda.fitpossible.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.AppUserDto;
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

    @PostMapping("/create")
    public void createUser(@Valid @RequestBody AppUserDto dto) {
        appUserService.create(dto);
    }

    @PostMapping("/update/{login}")
    public void updateUser(@Valid @RequestBody AppUserDto dto, @PathVariable String login) {
        appUserService.findUser(login);
        appUserService.update(login,dto);
    }

    @GetMapping("/{id}")
    public AppUserDto getUserById(@PathVariable long id) {
        return appUserService.findUser(id);
    }

    @GetMapping("/{login}")
    public AppUserDto getUserByLogin(@PathVariable String login) {
        return appUserService.findUser(login);
    }

    @GetMapping("/all")
    public List<AppUserDto> findAll() {
        return appUserService.findAll();
    }

    @GetMapping(value = "/delete/{login}")  //  zwracanie listy pozostałych?
    public void deleteUser(@PathVariable String login) {
        appUserService.delete(login);

    }


}
