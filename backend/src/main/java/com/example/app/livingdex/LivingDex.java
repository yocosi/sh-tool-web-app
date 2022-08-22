// This class is the Model.
// Also, it creates automatically the object table and sequence in the DB
package com.example.app.livingdex;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
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

    public LivingDex(Integer nbShinies) {
        this.nbShinies = nbShinies;
    }
}
