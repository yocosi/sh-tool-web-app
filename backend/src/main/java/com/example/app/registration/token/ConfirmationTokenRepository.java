// This class is a Repository.
// A Repository is used to perform the various activities on the object.
// These operating includes deletion, storage, retrieval, and also searching from the DB based on the particular criteria.
// It allows us to use methods like save, findById, findAll without having to implement those. Thanks to JpaRepository
package com.example.app.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    // The other way to do this :
    // @Query("SELECT c FROM ConfirmationToken c WHERE c.token = ?1")
    // This is JPQl, not straight SQL, equivalent of SELECT * FROM confirmationToken WHERE token = ??
    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    void updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);

}
