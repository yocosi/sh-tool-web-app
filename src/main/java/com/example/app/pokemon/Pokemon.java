package com.example.app.pokemon;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Pokemon() {
    }

    public Pokemon(Long id, String name, Integer nbEncounter, LocalDate startHuntDate, LocalDate encounterDate, Integer shinyRate, String huntMethod, String gameCatched) {
        this.id = id;
        this.name = name;
        this.nbEncounter = nbEncounter;
        this.startHuntDate = startHuntDate;
        this.encounterDate = encounterDate;
        this.shinyRate = shinyRate;
        this.huntMethod = huntMethod;
        this.gameCatched = gameCatched;
    }

    public Pokemon(String name, Integer nbEncounter, LocalDate startHuntDate, LocalDate encounterDate, Integer shinyRate, String huntMethod, String gameCatched) {
        this.name = name;
        this.nbEncounter = nbEncounter;
        this.startHuntDate = startHuntDate;
        this.encounterDate = encounterDate;
        this.shinyRate = shinyRate;
        this.huntMethod = huntMethod;
        this.gameCatched = gameCatched;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNbEncounter() {
        return nbEncounter;
    }

    public void setNbEncounter(Integer nbEncounter) {
        this.nbEncounter = nbEncounter;
    }

    public LocalDate getStartHuntDate() {
        return startHuntDate;
    }

    public void setStartHuntDate(LocalDate startHuntDate) {
        this.startHuntDate = startHuntDate;
    }

    public LocalDate getEncounterDate() {
        return encounterDate;
    }

    public void setEncounterDate(LocalDate encounterDate) {
        this.encounterDate = encounterDate;
    }

    public Integer getShinyRate() {
        return shinyRate;
    }

    public void setShinyRate(Integer shinyRate) {
        this.shinyRate = shinyRate;
    }

    public String getHuntMethod() {
        return huntMethod;
    }

    public void setHuntMethod(String huntMethod) {
        this.huntMethod = huntMethod;
    }

    public String getGameCatched() {
        return gameCatched;
    }

    public void setGameCatched(String gameCatched) {
        this.gameCatched = gameCatched;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nbEncounter=" + nbEncounter +
                ", startHuntDate=" + startHuntDate +
                ", encounterDate=" + encounterDate +
                ", shinyRate=" + shinyRate +
                ", huntMethod='" + huntMethod + '\'' +
                ", gameCatched='" + gameCatched + '\'' +
                '}';
    }
}
