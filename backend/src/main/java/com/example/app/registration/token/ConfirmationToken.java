// This class is the Model.
// Also, it creates automatically the object table and sequence in the DB
package com.example.app.registration.token;

import com.example.app.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class ConfirmationToken {

    @Id
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne // One user can have many confirmation token
    @JoinColumn(nullable = false, name = "user_id") // user_id is now a foreign key which take the id of the user newly registered
    private User user;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiredAt;
        this.user = user;
    }
}
