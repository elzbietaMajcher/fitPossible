package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.entity.UserRole;
import pl.sda.fitpossible.repository.UserRoleRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserRoleService {
    @Value("ROLE_USER")
    private String[] defaultRoles;


    @Autowired
   private UserRoleRepository userRoleRepository;

    public Set<UserRole> getDefaultUserRoles(){
        Set<UserRole> userRoles = new HashSet<>();
        for (String role : defaultRoles) {
            Optional<UserRole> singleRole = userRoleRepository.findByRoleName(role);
            if (singleRole.isPresent()) {
                userRoles.add(singleRole.get());
            }
        }
        return userRoles;
    }

}
