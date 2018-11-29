package pl.sda.fitpossible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {


    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


    @PostMapping("/create")
    public void create(@RequestBody FoodDto foodDto) {
        foodService.saveFood(foodDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        foodService.delateFood(id);
    }

    @GetMapping("/find/{id}")
    public FoodDto findUsingId(@PathVariable Long id) {
        FoodDto foodDto = foodService.findFoodById(id);
        return foodDto;
    }

//    @GetMapping(name = "/{name}")
//    public FoodDto findUsingName(@RequestParam String name){
//        FoodDto foodDto = foodService.findFoodByName(name);
//        return foodDto;
//    }

    @PutMapping("/{id}")
    public void editFood(@RequestBody FoodDto foodDto, @PathVariable("id") Long id) {
        foodService.updateFood(foodDto, id);
    }

    @RequestMapping("/")
    public String index() {
        return "Hello world.";
    }
}
