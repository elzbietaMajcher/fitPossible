package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.dto.AuthorizationUserData;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.UserRole;
import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.repository.UserRoleRepository;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Weight;
import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.repository.WeightRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;

    private WeightRepository weightRepository;

    public AppUserService(AppUserRepository appUserRepository,
                          WeightRepository weightRepository) {
        this.appUserRepository = appUserRepository;
        this.weightRepository = weightRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AppUserRoleService appUserRoleService;

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

    public AppUserDto findUser(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found."));
        return mapTo(appUser);
    }

    public AppUser find(Long id) {
        return appUserRepository.getOne(id);
    }

    public List<AppUserDto> findAll() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream().map(this::mapTo).collect(Collectors.toList());
    }

    public void update(Long id, AppUserDto dto) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found."));
        appUser.setPassword(dto.getPassword());
       // appUser.setLifestyle(dto.getLifestyle());
       // appUser.setEmail(dto.getEmail());
        appUserRepository.save(appUser);
    }

    public void delete(String login) {
        appUserRepository.deleteByLogin(login);
    }

    private AppUserDto mapTo(AppUser appUser) {
        AppUserDto dto = new AppUserDto();

        dto.setLogin(appUser.getLogin());
        dto.setPassword(appUser.getPassword());
       // dto.setEmail(appUser.getEmail());
        //dto.setDateOfBirth(appUser.getDateOfBirth());
        //dto.setGender(appUser.getGender());
       // dto.setHeight(appUser.getHeight());
      //  dto.setLifestyle(appUser.getLifestyle());
        return dto;
    }

    private AppUser mapTo(AppUserDto dto) {
        AppUser appUser = new AppUser();

        appUser.setLogin(dto.getLogin());
        appUser.setPassword(dto.getPassword());
        //appUser.setEmail(dto.getEmail());
       // appUser.setDateOfBirth(dto.getDateOfBirth());
        //appUser.setGender(dto.getGender());
        //appUser.setHeight(dto.getHeight());
       // appUser.setLifestyle(dto.getLifestyle());
        return appUser;
    }
}
