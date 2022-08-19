package com.example.app.livingdex;

import javax.persistence.*;

@Entity(name = "livingdex") // To map this class to a table in the DB
@Table(name = "livingdex") // And automatically generate the basic table of this class in the DB
public class LivingDex {
    @Id // id variable is now a primary key
    @SequenceGenerator(
            name = "livingdex_sequence",
            sequenceName = "livingdex_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "livingdex_sequence"
    )
    private Long id;
    private Integer nbShinies;

    public LivingDex() {
    }

    public LivingDex(Long id, Integer nbShinies) {
        this.id = id;
        this.nbShinies = nbShinies;
    }

    public LivingDex(Integer nbShinies) {
        this.nbShinies = nbShinies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbShinies() {
        return nbShinies;
    }

    public void setNbShinies(Integer nbShinies) {
        this.nbShinies = nbShinies;
    }

    @Override
    public String toString() {
        return "LivingDex{" +
                "id=" + id +
                ", nbShinies=" + nbShinies +
                '}';
    }
}
