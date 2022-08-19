package com.example.app.livingdex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LivingDexConfig {

    @Bean
    CommandLineRunner commandLineRunnerLivingDex(LivingDexRepository repository){
        return args -> {

        };
    }

}
