package com.example.app.shinyquest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShinyQuestRepository extends JpaRepository<ShinyQuest, Long> {

    // The other way to do this :
    // @Query("SELECT s FROM shinyQuest s WHERE s.id = ?1")
    // This is JPQl, not straight SQL, equivalent of SELECT * FROM shinyQuest WHERE id = ??
    Optional<ShinyQuest> findShinyQuestById(Long id);

}
