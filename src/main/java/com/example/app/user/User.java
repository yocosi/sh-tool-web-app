package com.example.app.user;

import javax.persistence.*;
import java.time.LocalDate;

// I name those "users" because "user" is a reserved keyword in postgresql
@Entity(name = "users") // To map this class to a table in the DB
@Table(name = "users")  // And automatically generate the basic table of this class in the DB
public class User {
    @Id // id variable is now a primary key
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate signUpDate;
    private Long livingDexId;

    public User() {
    }

    public User(Long id, String username, String email, String password, LocalDate signUpDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.signUpDate = signUpDate;
    }

    public User(String username, String email, String password, LocalDate signUpDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.signUpDate = signUpDate;
    }

    public User(String username, String email, String password, LocalDate signUpDate, Long livingDexId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.signUpDate = signUpDate;
        this.livingDexId = livingDexId;
    }

    public User(Long id, String username, String email, String password, LocalDate signUpDate, Long livingDexId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.signUpDate = signUpDate;
        this.livingDexId = livingDexId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Long getLivingDexId() {
        return livingDexId;
    }

    public void setLivingDexId(Long livingDexId) {
        this.livingDexId = livingDexId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", signUpDate=" + signUpDate +
                ", livingDexId=" + livingDexId +
                '}';
    }
}
