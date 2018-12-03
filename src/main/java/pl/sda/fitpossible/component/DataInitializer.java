package pl.sda.fitpossible.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.UserRole;
import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.repository.UserRoleRepository;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createInitialRoles();
        createInitialUsers();
    }

    private void createInitialUsers() {
        addUser("admin", "admin", "ROLE_USER", "ROLE_ADMIN");
        addUser("user", "user", "ROLE_USER");
}

    private void addUser(String login, String password, String... roles) {
        Set<UserRole> userRoles = new HashSet<>();
        for (String role : roles) {
            Optional<UserRole> singleRole = userRoleRepository.findByRoleName(role);
            if (singleRole.isPresent()){
                userRoles.add(singleRole.get());
            }
        }
        Optional<AppUser> searchedAppUser = appUserRepository.findByLogin(login);
        if (!searchedAppUser.isPresent()){
            AppUser appUser = new AppUser();
            appUser.setLogin(login);
            appUser.setPassword(passwordEncoder.encode(password));
            appUser.setRoles(userRoles);
            appUserRepository.save(appUser);
        }
    }

    private void createInitialRoles() {
        addRole("ROLE_USER");
        addRole("ROLE_ADMIN");
    }


    private void addRole(String roleName) {
        Optional<UserRole> searchRole = userRoleRepository.findByRoleName(roleName);
        if (!searchRole.isPresent()) {
            UserRole role = new UserRole();
            role.setRoleName(roleName);
            userRoleRepository.save(role);
        }
    }
}
