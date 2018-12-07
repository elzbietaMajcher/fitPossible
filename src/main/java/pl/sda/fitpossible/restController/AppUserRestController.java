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

    @GetMapping("/id/{id}") //ok
    public AppUserDto getUserById(@PathVariable long id) {
        return appUserService.findUser(id);
    }

   /* @GetMapping("/login/{login}")  //ok
    public AppUserDto getUserByLogin(@PathVariable String login) {
        return appUserService.findUser(login);
    }*/

    @GetMapping("/login/{login}")  //ok
    public AppUserDto getUserByLogin(@PathVariable String login) {
        return appUserService.findUser(login);
    }

    @GetMapping("/all")//ok
    public List<AppUserDto> findAll() {
        return appUserService.findAll();
    }

    @DeleteMapping(value = "/delete/{login}")  //ok + usówanie wagi
    public void deleteUser(@PathVariable String login) throws Exception {
        appUserService.delete(login);


    }


}
