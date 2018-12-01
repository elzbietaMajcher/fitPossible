package pl.sda.fitpossible.service;

import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Weight;
import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.repository.WeightRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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

    private void saveWeight(AppUserDto appUserDto, AppUser appUser){
        Weight weight = getWeight(appUserDto,appUser);
        weightRepository.save(weight);
    }

    private Weight getWeight(AppUserDto appUserDto, AppUser appUser) {
        Weight weight = new Weight();
        weight.setWeight(appUserDto.getWeight());
        weight.setOwner(appUser);
        return weight;
    }

    public void create(AppUserDto dto) {
        AppUser appUser = mapTo(dto);
        appUserRepository.save(appUser);

        saveWeight(dto, appUser);
    }

    public AppUserDto findUser(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found."));
        return mapTo(appUser);
    }

    public List<AppUserDto> findAll() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream().map(this::mapTo).collect(Collectors.toList());
    }

    public void update(Long id, AppUserDto dto) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found."));
        appUser.setPassword(dto.getPassword());
        appUser.setLifestyle(dto.getLifestyle());
        appUser.setEmail(dto.getEmail());
        appUserRepository.save(appUser);
    }

    public void delete(Long id) {
        appUserRepository.deleteById(id);
    }

    private AppUserDto mapTo(AppUser appUser) {
        AppUserDto dto = new AppUserDto();
        dto.setId(appUser.getId());
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
        appUser.setId(dto.getId());
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
