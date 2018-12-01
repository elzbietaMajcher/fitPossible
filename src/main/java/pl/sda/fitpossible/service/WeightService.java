package pl.sda.fitpossible.service;

import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.WeightDto;
import pl.sda.fitpossible.entity.Weight;
import pl.sda.fitpossible.repository.UserRepository;
import pl.sda.fitpossible.repository.WeightRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeightService {

    private WeightRepository weightRepository;

    public WeightService(UserRepository userRepository) {
        this.weightRepository = weightRepository;
    }

    public void add(WeightDto weightDto) {
        Weight weight = mapTo(weightDto);
        weightRepository.save(weight);
    }

    public List<WeightDto> getWeightHistory() {
        List<Weight> weightHistory = weightRepository.findAll();
        return weightHistory.stream().map(this::mapTo).collect(Collectors.toList());
    }

    private WeightDto mapTo(Weight weight) {
        WeightDto weightDto = new WeightDto();
        weightDto.setId(weight.getId());
        weightDto.setDate(weight.getDate());
        weightDto.setWeight(weight.getWeight());
        return weightDto;
    }

    private Weight mapTo(WeightDto weightDto) {
        Weight weight = new Weight();
        weight.setId(weightDto.getId());
        weight.setDate(weightDto.getDate());
        weight.setWeight(weightDto.getWeight());
        return weight;

    }
}
