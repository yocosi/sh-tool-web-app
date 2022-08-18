package com.example.app.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository repository){
        return args -> {
//            User yocosi = new User(1L,
//                    "Yocosi",
//                    "yocosi.dev@gmail.com",
//                    "blablabla",
//                    LocalDate.of(2022, Month.JANUARY, 5)
//            );
//
//            User viaiepi = new User(
//                    "Viaiepi",
//                    "viaiepi.dev@gmail.com",
//                    "bloubloublou",
//                    LocalDate.of(2021, Month.SEPTEMBER, 17)
//            );
//
//            repository.saveAll(List.of(yocosi, viaiepi));
        };
    }
}
