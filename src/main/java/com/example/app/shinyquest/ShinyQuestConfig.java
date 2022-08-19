package com.example.app.shinyquest;

import com.example.app.pokemon.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShinyQuestConfig {

    @Bean
    CommandLineRunner commandLineRunnerShinyQuest(PokemonRepository repository){
        return args -> {

        };
    }
}
