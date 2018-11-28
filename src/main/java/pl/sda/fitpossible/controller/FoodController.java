package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.service.FoodService;

@RestController
@RequestMapping(name = "/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping
    public void create(@RequestBody FoodDto foodDto){
        foodService.saveFood(foodDto);
    }

    @DeleteMapping(name = "/delete/{id}")
    public void delete(@RequestParam Long id){
        foodService.deldeleteFood(id);
    }

    @GetMapping(name = "/{id")
    public FoodDto findUsingId(@RequestParam Long id){
        FoodDto foodDto = foodService.findFoodById(id);
        return foodDto;
    }

    @GetMapping(name = "/{name}")
    public FoodDto findUsingName(@RequestParam String name){
        FoodDto foodDto = foodService.findFoodByName(name);
        return foodDto;
    }


}
