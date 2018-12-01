package pl.sda.fitpossible.service;

import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.repository.AppUserRepository;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {

//    private AppUserRepository appUserRepository;
//
//    public AppUserService(AppUserRepository appUserRepository) {
//        this.appUserRepository = appUserRepository;
//    }
//
//    public void create(AppUserDto dto) {
//       AppUser user = mapTo(dto);
//        appUserRepository.save(user);
//    }
//
//    public AppUserDto findUser(Long id) {
//        AppUser user = appUserRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("User not found."));
//        return mapTo(user);
//    }
//
//    public List<AppUserDto> findAll() {
//        List<AppUser> users = appUserRepository.findAll();
//        return users.stream().map(this::mapTo).collect(Collectors.toList());
//    }
//
//    public void update(Long id, AppUserDto dto) {
//        AppUser user = appUserRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("User not found."));
//        user.setPassword(dto.getPassword());
//        user.setLifestyle(dto.getLifestyle());
//        user.setEmail(dto.getEmail());
//        appUserRepository.save(user);
//    }
//
//    public void delete(Long id) {
//        appUserRepository.deleteById(id);
//    }
//
//    private AppUserDto mapTo(AppUser user) {
//        AppUserDto dto = new AppUserDto();
//        dto.setId(user.getId());
//        dto.setLogin(user.getLogin());
//        dto.setPassword(user.getPassword());
//        dto.setEmail(user.getEmail());
//        dto.setDateOfBirth(user.getDateOfBirth());
//        dto.setGender(user.getGender());
//        dto.setHeight(user.getHeight());
//        dto.setLifestyle(user.getLifestyle());
//        return dto;
//    }
//
//    private AppUser mapTo(AppUserDto dto) {
//        AppUser user = new AppUser();
//        user.setId(dto.getId());
//        user.setLogin(dto.getLogin());
//        user.setPassword(dto.getPassword());
//        user.setEmail(dto.getEmail());
//        user.setDateOfBirth(dto.getDateOfBirth());
//        user.setGender(dto.getGender());
//        user.setHeight(dto.getHeight());
//        user.setLifestyle(dto.getLifestyle());
//        return user;
//    }
}
