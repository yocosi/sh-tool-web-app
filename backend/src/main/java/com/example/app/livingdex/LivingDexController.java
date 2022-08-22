// This class is a controller.
// A controller is responsible for handling request from the clients and providing them with the responses as JSON/XML in return.
// Here we write our different HTTP endpoints.
// And then call, in every method, the corresponding Service who takes care of the business logic.
package com.example.app.livingdex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/livingdex")
public class LivingDexController {

    private final LivingDexService livingDexService;

    @Autowired
    public LivingDexController(LivingDexService livingDexService) {
        this.livingDexService = livingDexService;
    }

    @GetMapping
    public List<LivingDex> getAllLivingDex(){
        return livingDexService.getAllLivingDex();
    }

    @GetMapping(path = "{livingdexId}")
    public LivingDex getLivingDexById(@PathVariable("livingdexId") Long livingdexId){
        return livingDexService.getLivingDexById(livingdexId);
    }

    @PostMapping
    public void createNewLivingDex(@RequestBody LivingDex livingDex){
        livingDexService.createNewLivingDex(livingDex);
    }

    @DeleteMapping(path = "{livingdexId}")
    public void deleteLivingDex(@PathVariable("livingdexId") Long livingdexId){
        livingDexService.deleteLivingDex(livingdexId);
    }


    @PutMapping(path = "{livingdexId}")
    public void uptadeLivingDexTotalShinies(@PathVariable("livingdexId") Long livingdexId,
                                            @RequestParam(required = false) Integer nbShinies){
        livingDexService.updateLivingDexTotalShinies(livingdexId, nbShinies);
    }

}
