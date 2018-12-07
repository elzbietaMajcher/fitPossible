package pl.sda.fitpossible.restController;

import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.FoodDto;
import pl.sda.fitpossible.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodRestController {


    private FoodService foodService;
    public FoodRestController(FoodService foodService){this.foodService = foodService;}

    @PostMapping("/create") // ok
    public void create(@RequestBody FoodDto foodDto){
        foodService.createFood(foodDto);
    }

    @DeleteMapping("/delete/{id}")  // ok
    public void delete(@PathVariable Long id){
        foodService.deleteFood(id);
    }

    @GetMapping("/find/{id}")  // ok
    public FoodDto findUsingId(@PathVariable Long id){
        return foodService.findFood(id);
    }

    @PutMapping("/update/{id}") //ok
    public void update( @RequestBody FoodDto foodDto,@PathVariable Long id ){
        foodService.updateFood(id, foodDto);
    }

//    @GetMapping(name = "/{name}")
//    public FoodDto findUsingName(@PathVariable String name){
//        FoodDto foodDto = foodService.findFoodByName(name);
//        return foodDto;
//    }

}
