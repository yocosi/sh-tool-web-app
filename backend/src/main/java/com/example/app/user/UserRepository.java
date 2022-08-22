// This class is a Repository.
// A Repository is used to perform the various activities on the object.
// These operating includes deletion, storage, retrieval, and also searching from the DB based on the particular criteria.
// It allows us to use methods like save, findById, findAll without having to implement those. Thanks to JpaRepository
package com.example.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // The other way to do this :
    // @Query("SELECT u FROM User u WHERE u.email = ?1")
    // This is JPQl, not straight SQL, equivalent of SELECT * FROM users WHERE email = ??
    Optional<User> findUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE users u " +
            "SET u.enabled = TRUE WHERE u.email = ?1")
    void enableUser(String email);

}
