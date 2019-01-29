package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.dto.NutritionHistoryDto;
import pl.sda.fitpossible.entity.Food;
import pl.sda.fitpossible.repository.FoodRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private NutritionHistoryService nutritionHistoryService;
    private FoodRepository foodRepository;
    public FoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
       }




    public void createFood(FoodDto foodDto){
        Food food = mapTo(foodDto);
        foodRepository.save(food);
    }

    public void deleteFood(Long id){
       foodRepository.deleteById(id);
    }

    public void updateFood(Long id, FoodDto foodDto){
        Optional<Food> food = foodRepository.findById(id);
        Food findFood = food
                .orElseThrow(()-> new EntityNotFoundException("Item not found " + id));
        findFood.setName(foodDto.getName());
        findFood.setCaloriesPerUnit(foodDto.getCaloriesPerUnit());
        findFood.setUnit(foodDto.getUnit());
        foodRepository.save(findFood);
    }


    public FoodDto findFood(Long id){
        Optional<Food> food = foodRepository.findById(id);
        Food findFood = food
                .orElseThrow(()-> new EntityNotFoundException("Item not found " + id));
        return mapTo(findFood);
    }

    public List<FoodDto> findAll(){
        List<Food> foods = foodRepository.findAll();
        return foods.stream().map(this::mapTo).collect(Collectors.toList());
    }

    public FoodDto findByName(String name){
        Food food = foodRepository.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException("Item not found " + name));
        FoodDto foodDto = mapTo(food);
        return  foodDto;
    }



    public Food mapTo(FoodDto foodDto) {
        Food food =new Food();
        food.setId(foodDto.getId());
        food.setName(foodDto.getName());
        food.setCaloriesPerUnit(foodDto.getCaloriesPerUnit());
        food.setUnit(foodDto.getUnit());
        return food;
    }

    private FoodDto mapTo(Food food){
        FoodDto foodDto = new FoodDto();
        foodDto.setId(food.getId());
        foodDto.setName(food.getName());
        foodDto.setCaloriesPerUnit(food.getCaloriesPerUnit());
        foodDto.setUnit(food.getUnit());
        return foodDto;
    }
}
