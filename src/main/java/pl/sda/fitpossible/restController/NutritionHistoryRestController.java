package pl.sda.fitpossible.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.fitpossible.dto.NutritionHistoryDto;
import pl.sda.fitpossible.service.NutritionHistoryService;

import java.util.List;

@RestController
@RequestMapping("nutrition_history")
public class NutritionHistoryRestController {
    @Autowired
    private NutritionHistoryService nutritionHistoryService;

    public NutritionHistoryRestController(NutritionHistoryService nutritionHistoryService) {
        this.nutritionHistoryService = nutritionHistoryService;
    }

    @PostMapping("/add/{login}")  // ok
    public void addNutrition(@RequestBody NutritionHistoryDto nutritionHistoryDto, @PathVariable String login) {
        nutritionHistoryService.createNutritionHistory(nutritionHistoryDto, login);
    }

    @GetMapping("/find_all/{login}")  // ok
    public List<NutritionHistoryDto> getNutritionHistoryByLogin(@PathVariable String login) {
        return nutritionHistoryService.findAll(login);
    }

    @PutMapping("/update/{id}")  // ok
    public void updateMealTime(@RequestBody NutritionHistoryDto nutritionHistoryDto, @PathVariable Long id) {
        nutritionHistoryService.findOneMeal(id);
        nutritionHistoryService.update(id, nutritionHistoryDto);
    }


}
