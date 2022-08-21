package com.example.app.shinyquest;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
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
}
