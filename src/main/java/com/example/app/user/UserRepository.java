package com.example.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // The other way to do this :
    // @Query("SELECT u FROM User u WHERE u.email = ?1")
    // This is JPQl, not straight SQL, equivalent of SELECT * FROM users WHERE email = ??
    Optional<User> findUserByEmail(String email);

}
