package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.NutritionHistory;
import pl.sda.fitpossible.entity.Weight;
import pl.sda.fitpossible.repository.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ActivityHistoryRepository activityHistoryRepository;
    @Autowired
    private NutritonHistoryRepository nutritonHistoryRepository;
    @Autowired
    private AppUserRoleService appUserRoleService;

    @Autowired
    private WeightRepository weightRepository;

    private AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository,
                          WeightRepository weightRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void create(AppUserDto dto) { //ok
        AppUser user = new AppUser();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(appUserRoleService.getDefaultUserRoles());
        appUserRepository.save(user);
    }


   /* public AppUserDto findUser(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found."));
        return mapTo(appUser);
    }*/

    /*public AppUserDto findUser(String login, String password) {  //??
        AppUser appUser = appUserRepository.findAppUserByLoginEqualsIgnoringCaseAndPassword(login, passwordEncoder(password))
                .orElseThrow(() -> new EntityNotFoundException("AppUser" + login + " not found."));
        return mapTo(appUser);
    }*/
    public AppUserDto findUser(String login) {  //??
        AppUser appUser = appUserRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("AppUser" + login + " not found."));
        return mapTo(appUser);
    }

    public AppUserDto findUser(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found."));
        return mapTo(appUser);
    }

    public List<AppUserDto> findAll() {  //ok
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream().map(this::mapTo).collect(Collectors.toList());
        /* appUsers.stream().map(this::mapTo).collect(Collectors.toList());*/
    }

    public void update(String login, AppUserDto dto) {
        AppUser appUser = appUserRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("AppUser " + login + " not found."));
        appUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        appUser.setEmail(dto.getEmail());
        appUser.setDateOfBirth(dto.getDateOfBirth());
        appUser.setGender(dto.getGender());
        appUser.setHeight(dto.getHeight());
        appUser.setLifestyle(dto.getLifestyle());
        appUserRepository.save(appUser);
    }

    public void delete(String login) throws Exception {
        AppUser userToDelete = appUserRepository.findByLogin(login)
                .orElseThrow(Exception::new);

        List<Weight> weightByOwner = weightRepository.findAllByUserLogin(login);
        for (Weight weight : weightByOwner) {
            weightRepository.delete(weight);
        }

        List<ActivityHistory>activityHistoryByOwner = activityHistoryRepository.findAllByUserLogin(login);
        for(ActivityHistory history:activityHistoryByOwner){
            activityHistoryRepository.delete(history);
        }
        appUserRepository.delete(userToDelete);

        List<NutritionHistory>nutritionHistoryByOwner=nutritonHistoryRepository.findAllByUserLogin(login);
        for(NutritionHistory history:nutritionHistoryByOwner){
            nutritonHistoryRepository.delete(history);
        }
    }


    private AppUserDto mapTo(AppUser appUser) {
        AppUserDto dto = new AppUserDto();

        dto.setLogin(appUser.getLogin());
        dto.setPassword(appUser.getPassword());
        dto.setEmail(appUser.getEmail());
        dto.setDateOfBirth(appUser.getDateOfBirth());
        dto.setGender(appUser.getGender());
        dto.setHeight(appUser.getHeight());
        dto.setLifestyle(appUser.getLifestyle());
        return dto;
    }

    private AppUser mapTo(AppUserDto dto) {
        AppUser appUser = new AppUser();

        appUser.setLogin(dto.getLogin());
        appUser.setPassword(dto.getPassword());
        appUser.setEmail(dto.getEmail());
        appUser.setDateOfBirth(dto.getDateOfBirth());
        appUser.setGender(dto.getGender());
        appUser.setHeight(dto.getHeight());
        appUser.setLifestyle(dto.getLifestyle());
        return appUser;
    }
}
