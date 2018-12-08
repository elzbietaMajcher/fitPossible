package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.AddUserData;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.dto.AuthorizationUserData;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.entity.NutritionHistory;
import pl.sda.fitpossible.entity.Weight;
import pl.sda.fitpossible.repository.*;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private AppUserRepository appUserRepository;


    public void create(AppUserDto dto) { //ok
        AppUser user = new AppUser();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(appUserRoleService.getDefaultUserRoles());
        appUserRepository.save(user);
    }

    public Optional<AppUser> registry(AuthorizationUserData authorizationUserData) {

        Optional<AppUser> optionalAppUser = appUserRepository.findByLogin(authorizationUserData.getLogin());
        if (optionalAppUser.isPresent()) {
            return Optional.empty();
        }
        AppUser appUser = new AppUser();
        appUser.setLogin(authorizationUserData.getLogin());
        appUser.setPassword(passwordEncoder.encode(authorizationUserData.getPassword()));
        appUser.setRoles(appUserRoleService.getDefaultUserRoles());

        return Optional.of(appUserRepository.save(appUser));
    }

    public AppUserDto findUserByLogin(String login) {
        AppUser appUser = appUserRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found."));
        return mapTo(appUser);
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

        List<ActivityHistory> activityHistoryByOwner = activityHistoryRepository.findAllByUserLogin(login);
        for (ActivityHistory history : activityHistoryByOwner) {
            activityHistoryRepository.delete(history);
        }

        List<NutritionHistory> nutritionHistoryByOwner = nutritonHistoryRepository.findAllByAppUserId(login);
        for (NutritionHistory history : nutritionHistoryByOwner) {
            nutritonHistoryRepository.delete(history);
        }
        appUserRepository.delete(userToDelete);
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

    //
    public void updateAppUserData(String login, AddUserData dto) throws ParseException {
        AppUser appUser = appUserRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("AppUser " + login + " not found."));

        appUser.setEmail(dto.getEmail());
        //appUser.setDateOfBirth(getAddBirthData(dto.getDateOfBirth()));
        appUser.setGender(dto.getGender());
        appUser.setHeight(dto.getHeight());
        appUser.setLifestyle(dto.getLifestyle());
        appUserRepository.save(appUser);
    }


    private Date getAddBirthData(String inputDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      return format.parse(inputDate);
    }

//    public void updateAppUserData(String login, AppUserDto appUserDto) {
//        Optional<AppUser> appUserOptional = appUserRepository.findByLogin(login);
//        AppUser appUser = appUserOptional
//                .orElseThrow(() -> new EntityNotFoundException("User not found " + login));
//        appUser = mapTo(appUserDto);
//        appUserRepository.save(appUser);
//
//    }
//
//    public void updateData(String login, AddUserData dto) {
//        Optional<AppUser> appUserOptional = appUserRepository.findByLogin(login);
//        AppUser appUser = appUserOptional
//                .orElseThrow(() -> new EntityNotFoundException("User not found " + login));
//        appUser=mapTo(dto);
//        appUserRepository.save(appUser);
//
//    }

}
