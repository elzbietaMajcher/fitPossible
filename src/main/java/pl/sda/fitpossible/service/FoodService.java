package pl.sda.fitpossible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.entity.Food;
import pl.sda.fitpossible.repository.FoodRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public void saveFood(FoodDto foodDto){
        Food food = new Food();
        food = map(foodDto);
        foodRepository.save(food);
    }

    public void deldeleteFood(Long id){
       foodRepository.findById(id);
    }

    public void updateFood(Long id, FoodDto foodDto){
        Food food = foodRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Task not found " + id));
        food=map(foodDto);
        foodRepository.save(food);
    }

    public FoodDto findFoodById(Long id){
        Food food = foodRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Task not found " + id));
        FoodDto foodDto = map(food);
        return foodDto;
    }

    public FoodDto findFoodByName(String name){
        Food food = foodRepository.findByName(name)
                .orElseThrow(()-> new EntityNotFoundException("Task not found " + name));
        FoodDto foodDto = map(food);
        return  foodDto;
    }



    private Food map(FoodDto foodDto) {
        Food food =new Food();
        food.setId(foodDto.getId());
        food.setName(foodDto.getName());
        food.setCaloriesPerUnit(foodDto.getCaloriesPerUnit());
        food.setUnit(foodDto.getUnit());
        return food;
    }

    private FoodDto map(Food food){
        FoodDto foodDto = new FoodDto();
        foodDto.setId(food.getId());
        foodDto.setName(food.getName());
        foodDto.setCaloriesPerUnit(food.getCaloriesPerUnit());
        foodDto.setUnit(food.getUnit());
        return foodDto;
    }
}
