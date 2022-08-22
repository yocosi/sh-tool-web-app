// This class is a Repository.
// A Repository is used to perform the various activities on the object.
// These operating includes deletion, storage, retrieval, and also searching from the DB based on the particular criteria.
// It allows us to use methods like save, findById, findAll without having to implement those. Thanks to JpaRepository
package com.example.app.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    // The other way to do this :
    // @Query("SELECT p FROM Pokémon p WHERE p.name = ?1")
    // This is JPQl, not straight SQL, equivalent of SELECT * FROM pokemon WHERE name = ??
    Optional<Pokemon> findPokemonByName(String name);

}
