package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.service.AppUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class AppUserRestController {

//    @Autowired
//    private AppUserService appUserService;
//
//    public AppUserRestController(AppUserService appUserService) {
//        this.appUserService = appUserService;
//    }
//
//    @PostMapping("/create")
//    public void createUser(@Valid @RequestBody AppUserDto dto) {
//        appUserService.create(dto);
//        }
//
//    @GetMapping("/{id}")
//    public AppUserDto getUserById (@PathVariable long id){
//        return appUserService.findUser(id);
//    }
//
//
//    @GetMapping("/all")
//    public List<AppUserDto> findAll() {
//        return appUserService.findAll();
//    }
//
//    @GetMapping(value = "/delete/{id}")  //  zwracanie listy pozostałych?
//    public void deleteUser (@PathVariable long id){
//        appUserService.delete(id);
//
//    }


}
