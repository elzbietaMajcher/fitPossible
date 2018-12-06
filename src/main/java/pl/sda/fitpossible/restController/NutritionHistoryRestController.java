package pl.sda.fitpossible.restController;

import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.NutritionHistoryDto;
import pl.sda.fitpossible.service.NutritionHistoryService;

import java.util.List;

@RestController
@RequestMapping("nutrition_history")
public class NutritionHistoryRestController {
    private NutritionHistoryService nutritionHistoryService;

    public NutritionHistoryRestController(NutritionHistoryService nutritionHistoryService) {
        this.nutritionHistoryService = nutritionHistoryService;
    }

    @PostMapping("/add")  // ok
    public void addNutrition(@RequestBody NutritionHistoryDto nutritionHistoryDto) {
        nutritionHistoryService.createNutritionHistory(nutritionHistoryDto);
    }

    @GetMapping("/all")  // ok
    public List<NutritionHistoryDto> findAll() {
        return nutritionHistoryService.findAll();
    }

    @PutMapping("/update/{id}")  // ok
    public void updateMealTime (@RequestBody NutritionHistoryDto nutritionHistoryDto, @PathVariable Long id){
        nutritionHistoryService.findOneMeal(id);
        nutritionHistoryService.update(id, nutritionHistoryDto);


    }


}
