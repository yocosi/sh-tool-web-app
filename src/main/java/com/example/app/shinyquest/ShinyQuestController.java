package com.example.app.shinyquest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/shinyquest")
public class ShinyQuestController {

    private final ShinyQuestService shinyQuestService;

    @Autowired
    public ShinyQuestController(ShinyQuestService shinyQuestService) {
        this.shinyQuestService = shinyQuestService;
    }

    @GetMapping
    public List<ShinyQuest> getAllShinyQuests(){
        return shinyQuestService.getAllShinyQuests();
    }

    @GetMapping(path = "{shinyquestId}")
    public ShinyQuest getShinyQuestById(@PathVariable("shinyquestId") Long shinyquestId){
        return shinyQuestService.getShinyQuestById(shinyquestId);
    }

    @PostMapping
    public void addNewShinyQuest(@RequestBody ShinyQuest shinyQuest){
        shinyQuestService.addNewShinyQuest(shinyQuest);
    }

    @DeleteMapping(path = "{shinyquestId}")
    public void deleteShinyQuest(@PathVariable("shinyquestId") Long shinyquestId){
        shinyQuestService.deleteShinyQuest(shinyquestId);
    }

    @PutMapping(path = "{shinyquestId}")
    public void updateShinyQuest(@PathVariable("shinyquestId") Long shinyquestId,
                                 @RequestParam(required = false) String type,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) LocalDate startDate,
                                 @RequestParam(required = false) LocalDate endDate,
                                 @RequestParam(required = false) String game){
        shinyQuestService.updateShinyQuest(shinyquestId, type, name, startDate, endDate, game);
    }

}
