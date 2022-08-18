package com.example.app.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service // For the dependency injection of this class
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getPokemon(){
        return pokemonRepository.findAll();
    }

    public Pokemon getPokemonByName(Pokemon pokemon){
        return pokemonRepository.findPokemonByName(pokemon.getName()).get();
    }

    public void addNewPokemon(Pokemon pokemon){
        Optional<Pokemon> pokemonOptional = pokemonRepository.findPokemonByName(pokemon.getName());
        if (pokemonOptional.isPresent()){
            throw new IllegalStateException("This pokemon already exists in the database");
        }
        pokemonRepository.save(pokemon);
    }

    public void deletePokemon(Long pokemonId){
        boolean exists = pokemonRepository.existsById(pokemonId);
        if (!exists){
            throw new IllegalStateException("This pokemon doesn't exist. Unable to delete it.");
        }
        pokemonRepository.deleteById(pokemonId);
    }

    // With transactional annotation this is not necessary to implement jpql query
    // Because with this annotation the entity goes into a managed state
    @Transactional
    public void updatePokemon(Long pokemonId,
                           String name,
                           Integer nbEncounter,
                           LocalDate startHuntDate,
                           LocalDate encounterDate,
                           Integer shinyRate,
                           String huntMethod,
                           String gameCatched){
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new IllegalStateException("Pokemon with id " + pokemonId + " does not exists"));

        if(name != null && name.length() > 0 && !Objects.equals(pokemon.getName(), name)){
            pokemon.setName(name);
        }

        if (nbEncounter != null && nbEncounter > 0 && !Objects.equals(pokemon.getNbEncounter(), nbEncounter)){
            pokemon.setNbEncounter(nbEncounter);
        }

        if (startHuntDate != null &&  startHuntDate.compareTo(LocalDate.now().plusDays(1)) < 0 && !Objects.equals(pokemon.getStartHuntDate(), startHuntDate)){
            pokemon.setStartHuntDate(startHuntDate);
        }

        if (encounterDate != null && !(encounterDate.compareTo(pokemon.getStartHuntDate()) < 0) && !Objects.equals(pokemon.getEncounterDate(), encounterDate)){
            pokemon.setEncounterDate(encounterDate);
        }

        if(shinyRate != null && shinyRate > 0 && shinyRate < 8192 && !Objects.equals(pokemon.getShinyRate(), shinyRate)){
            pokemon.setShinyRate(shinyRate);
        }

        if (huntMethod != null && huntMethod.length() > 0 && !Objects.equals(pokemon.getHuntMethod(), huntMethod)){
            pokemon.setHuntMethod(huntMethod);
        }

        if (gameCatched != null && gameCatched.length() > 0 && !Objects.equals(pokemon.getGameCatched(), gameCatched)){
            pokemon.setGameCatched(gameCatched);
        }
    }

}
