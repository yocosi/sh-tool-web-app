package com.example.app.pokemon;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "pokemon") // To map this class to a table in the DB
@Table(name = "pokemon") // And automatically generate the basic table of this class in the DB
public class Pokemon {

    @Id // id variable is now a primary key
    @SequenceGenerator(
            name = "pokemon_sequence",
            sequenceName = "pokemon_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pokemon_sequence"
    )
    private Long id;
    private String name;
    private Integer nbEncounter;
    private LocalDate startHuntDate;
    private LocalDate encounterDate;
    private Integer shinyRate;
    private String huntMethod;
    private String gameCatched;

    public Pokemon(String name, Integer nbEncounter, LocalDate startHuntDate, LocalDate encounterDate, Integer shinyRate, String huntMethod, String gameCatched) {
        this.name = name;
        this.nbEncounter = nbEncounter;
        this.startHuntDate = startHuntDate;
        this.encounterDate = encounterDate;
        this.shinyRate = shinyRate;
        this.huntMethod = huntMethod;
        this.gameCatched = gameCatched;
    }
}
