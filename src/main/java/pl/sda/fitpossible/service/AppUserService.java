package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.UserRole;
import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.repository.UserRoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AppUserRoleService appUserRoleService;

    public void create(AppUserDto dto) {
        AppUser user = new AppUser();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(appUserRoleService.getDefaultUserRoles());
        appUserRepository.save(user);
    }

    public AppUserDto findUser(Long id) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return mapTo(user);
    }

    public List<AppUserDto> findAll() {
        List<AppUser> users = appUserRepository.findAll();
        return users.stream().map(this::mapTo).collect(Collectors.toList());
    }

    public void update(Long id, AppUserDto dto) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        user.setPassword(dto.getPassword());
        appUserRepository.save(user);
    }

    public void delete(Long id) {
        appUserRepository.deleteById(id);
    }

    private AppUserDto mapTo(AppUser user) {
        AppUserDto dto = new AppUserDto();

        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        return dto;
    }

    private AppUser mapTo(AppUserDto dto) {
        AppUser user = new AppUser();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        return user;
    }


}
