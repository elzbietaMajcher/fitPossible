package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.dto.WeightDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Weight;

import pl.sda.fitpossible.repository.AppUserRepository;
import pl.sda.fitpossible.repository.WeightRepository;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeightService {

    private WeightRepository weightRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    //@Autowired
    public WeightService(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    public void addWeight(WeightDto weightDto, String login) {

        //TODO Date date = Date.from(Instant.from(LocalDate.now()));
        Weight weight = mapTo(weightDto);
        AppUser owner = findUser(login);
        weight.setUser(owner);
        //TODO weight.setDate(date);
        weightRepository.save(weight);
    }

    public List<WeightDto> getWeightHistory(String login) {  // for one user
        List<Weight> weightHistory = weightRepository.findAllByUserLogin(login);
        return weightHistory.stream().map(this::mapTo).collect(Collectors.toList());
    }

    private AppUser findUser(String login) { //TODO some classes use exactly the same method
        return appUserRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("AppUser" + login + " not found."));

    }

    private WeightDto mapTo(Weight weight) {
        WeightDto weightDto = new WeightDto();
        weightDto.setDate(weight.getDate());
        weightDto.setWeight(weight.getWeight());
        return weightDto;
    }

    private Weight mapTo(WeightDto weightDto) {
        Weight weight = new Weight();
        weight.setDate(weightDto.getDate());
        weight.setWeight(weightDto.getWeight());
        /*AppUser appUser = appUserService.findUser(weightDto.getUser_id());
        weight.setUser(mapTo(appUser));*/

        return weight;

    }
}
