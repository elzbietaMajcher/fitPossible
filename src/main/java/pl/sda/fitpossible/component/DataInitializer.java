package pl.sda.fitpossible.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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
public class DataInitializer /*implements ApplicationListener<ContextRefreshedEvent> */{
//
//    @Autowired
//    private AppUserRepository appUserRepository;
//
//    @Autowired
//    private UserRoleRepository userRoleRepository;
//
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        createInitialRoles();
//        createInitialUsers();
//    }
//
//    private void createInitialUsers() {
//        addUser("admin","password","ROLE_USER","ROLE_ADMIN" );
//    }
//
//    private void addUser(String username, String password, String role_user, String... roles) {
//        Set<UserRole> userRoles = new HashSet<>();
//        for (String role : roles) {
//            Optional<UserRole> singleRole = userRoleRepository.findByName(role);
//            if (singleRole.isPresent()){
//                userRoles.add(singleRole.get());
//            }
//        }
//        Optional<AppUser> searchedAppUser = appUserRepository.findByLogin(username);
//        if (!searchedAppUser.isPresent()){
//            AppUser appUser = AppUser.builder()
//                    .login(username)
//                    .password(password)
//                    .roles(userRoles).build();
//            appUserRepository.save(appUser);
//        }
//    }
//
//    private void createInitialRoles() {
//        addRole("ROLE_USER");
//        addRole("ROLE_ADMIN");
//    }
//
//    private void addRole(String name) {
//        Optional<UserRole> searchRole = userRoleRepository.findByName(name);
//        if (!searchRole.isPresent()) {
//            UserRole role = new UserRole();
//            role.setName(name);
//            userRoleRepository.save(role);
//        }
//    }
}
