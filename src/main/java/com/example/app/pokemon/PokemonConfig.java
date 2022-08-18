package com.example.app.pokemon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PokemonConfig {

    @Bean
    CommandLineRunner commandLineRunnerPokemon(PokemonRepository repository){
        return args -> {

        };
    }

}


