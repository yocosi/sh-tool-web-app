package com.example.app.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired // Dependency Injection of PokemonService. To be able to use it
    public PokemonController(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getPokemon(){
        return pokemonService.getPokemon();
    }

    @GetMapping(path = "{pokemonId}")
    public Pokemon getPokemonById(@PathVariable("pokemonId") Long pokemonId){
        return pokemonService.getPokemonById(pokemonId);
    }

    @PostMapping
    public void addNewPokemon(@RequestBody Pokemon pokemon){
        pokemonService.addNewPokemon(pokemon);
    }

    @DeleteMapping(path = "{pokemonId}")
    public void deletePokemon(@PathVariable("pokemonId") Long pokemonId){
        pokemonService.deletePokemon(pokemonId);
    }

    @PutMapping(path = "{pokemonId}")
    public void updatePokemon(@PathVariable("pokemonId") Long pokemonId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer nbEncounter,
                              @RequestParam(required = false) LocalDate startHuntDate,
                              @RequestParam(required = false) LocalDate encounterDate,
                              @RequestParam(required = false) Integer shinyRate,
                              @RequestParam(required = false) String huntMethod,
                              @RequestParam(required = false) String gameCatched){
        pokemonService.updatePokemon(pokemonId, name, nbEncounter, startHuntDate, encounterDate, shinyRate, huntMethod, gameCatched);
    }

}
