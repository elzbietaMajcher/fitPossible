package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {


    private FoodService foodService;
    public FoodController(FoodService foodService){this.foodService = foodService;}

    @PostMapping("/create")
    public void create(@RequestBody FoodDto foodDto){
        foodService.saveFood(foodDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        foodService.deleteFood(id);
    }

    @GetMapping("/find/{id}")
    public void findUsingId(@PathVariable Long id){
        foodService.findFoodById(id);
    }

    @PutMapping("/update/{id}")
    public void update( @RequestBody FoodDto foodDto,@PathVariable Long id ){
        foodService.updateFood(id, foodDto);
    }

//    @GetMapping(name = "/{name}")
//    public FoodDto findUsingName(@PathVariable String name){
//        FoodDto foodDto = foodService.findFoodByName(name);
//        return foodDto;
//    }

}
