package com.example.app.shinyquest;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "shinyquest")
@Table(name = "shinyquest")
public class ShinyQuest {
    @Id
    @SequenceGenerator(
            name = "shinyquest_sequence",
            sequenceName = "shinyquest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shinyquest_sequence"
    )
    private Long id;
    private String type;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String game;

    public ShinyQuest() {
    }

    public ShinyQuest(Long id, String type, String name, LocalDate startDate, LocalDate endDate, String game) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.game = game;
    }

    public ShinyQuest(Long id, String type, String name, LocalDate startDate, String game) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.startDate = startDate;
        this.game = game;
    }

    public ShinyQuest(String type, String name, LocalDate startDate, LocalDate endDate, String game) {
        this.type = type;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.game = game;
    }

    public ShinyQuest(String type, String name, LocalDate startDate, String game) {
        this.type = type;
        this.name = name;
        this.startDate = startDate;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "ShinyQuest{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", game='" + game + '\'' +
                '}';
    }
}
