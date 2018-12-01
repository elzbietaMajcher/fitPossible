package pl.sda.fitpossible.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.service.FoodService;

import javax.validation.Valid;

@RestController
@RequestMapping("/food")
public class FoodRestController {


    private FoodService foodService;
    public FoodRestController(FoodService foodService){this.foodService = foodService;}

    @PostMapping("/create")
    public void create(@Valid @RequestBody FoodDto foodDto){
        foodService.createFood(foodDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        foodService.deleteFood(id);
    }

    @GetMapping("/find/{id}")
    public void findUsingId(@PathVariable Long id){
        foodService.findFood(id);
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
