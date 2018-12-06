package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.dto.WeightDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Weight;

import pl.sda.fitpossible.repository.WeightRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeightService {

    private WeightRepository weightRepository;
    @Autowired
    private AppUserService appUserService;

    @Autowired
    public WeightService(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    public void addWeight(WeightDto weightDto, AppUser owner) {
        Weight weight = mapTo(weightDto);
        weight.setUser(owner);
        weightRepository.save(weight);
    }

    public List<WeightDto> getWeightHistory(AppUser owner) {  // for one user
        List<Weight> weightHistory = weightRepository.findAllByUser(owner);
        return weightHistory.stream().map(this::mapTo).collect(Collectors.toList());
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
        //AppUser appUser = appUserService.find(weightDto.getUser_id());
        //weight.setUser(appUser);

        return weight;

    }
}
